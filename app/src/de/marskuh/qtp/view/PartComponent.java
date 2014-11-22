package de.marskuh.qtp.view;

import de.marskuh.qtp.ApplicationContext;
import de.marskuh.qtp.ImageManager;
import de.marskuh.qtp.model.Part;

import javax.swing.*;
import java.awt.*;

public class PartComponent extends JLabel {

    private final Part part;

    private final ImageManager imageManager = ApplicationContext.getInstance().getImageManager();

    public PartComponent(Part part) {
        this.part = part;
        setOpaque(false);
        setIcon(new ImageIcon(imageManager.getImage(part.getImage(), 100, 50)));
        setPreferredSize(new Dimension(100, 50));
        setToolTipText(part.getDescription());
    }

    public Part getPartModel() {
        return part;
    }
}
