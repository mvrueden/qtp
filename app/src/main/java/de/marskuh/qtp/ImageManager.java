package de.marskuh.qtp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ImageManager {

    public java.awt.Image getImage(String location, int width, int height) {
        java.awt.Image image = getImage(location);
        return image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    }

    public java.awt.Image getImage(String location) {
        InputStream inputStream = Main.class.getResourceAsStream(location);
        Objects.requireNonNull(inputStream, "Image " + location + " not found.");
        BufferedImage image = null;
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            //TODO handle this error case appropriately
            e.printStackTrace();
        }
        return image;
    }

    public java.awt.Image getImage(Image image) {
        return getImage(image.location);
    }

    public java.awt.Image getImage(String location, float scaleFactor) {
        java.awt.Image original = getImage(location);
        BufferedImage copy = rescale((BufferedImage) original, scaleFactor, 0);
        return copy;
    }

    private static BufferedImage rescale(BufferedImage indexed, float scaleFactor, float offset) {

        IndexColorModel icm = (IndexColorModel) indexed.getColorModel();

        return new BufferedImage(rescale(icm, scaleFactor, offset), indexed.getRaster(), false, null);

    }

    private static IndexColorModel rescale(IndexColorModel icm, float scaleFactor, float offset) {
        int size = icm.getMapSize();
        byte[] reds=new byte[size], greens=new byte[size], blues=new byte[size], alphas=new byte[size];

        icm.getReds(reds);
        icm.getGreens(greens);
        icm.getBlues(blues);
        icm.getAlphas(alphas);

        rescale(reds, scaleFactor, offset);
        rescale(greens, scaleFactor, offset);
        rescale(blues, scaleFactor, offset);

        return new IndexColorModel(8, size, reds, greens, blues, alphas);
    }


    private static void rescale(byte[] comps, float scaleFactor, float offset) {
        for (int i = 0; i < comps.length; ++i) {
            int comp = 0xff & comps[i];
            int newComp = Math.round(comp * scaleFactor + offset);
            if (newComp < 0) {
                newComp = 0;
            } else if (newComp > 255) {
                newComp = 255;
            }
            comps[i] = (byte) newComp;
        }
    }

    public static enum Image {
        Logo("/images/logo.png");

        private final String location;

        private Image(String location) {
            this.location = location;
        }
    }

}
