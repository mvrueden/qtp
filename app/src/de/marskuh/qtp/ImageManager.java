package de.marskuh.qtp;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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

    public static enum Image {
        Logo("/images/logo.png");

        private final String location;

        private Image(String location) {
            this.location = location;
        }
    }

}
