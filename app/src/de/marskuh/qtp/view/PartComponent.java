package de.marskuh.qtp.view;

import de.marskuh.qtp.ApplicationContext;
import de.marskuh.qtp.ImageManager;
import de.marskuh.qtp.model.Part;

import javax.swing.*;
import java.awt.*;

public class PartComponent extends JLabel {

    private final Part part;

    private final ImageManager imageManager = ApplicationContext.getInstance().getImageManager();

    public PartComponent(Part part, int width, int height) {
        this.part = part;
        setOpaque(false);
        setIcon(new ImageIcon(imageManager.getImage(part.getImage(), width, height)));
        setPreferredSize(new Dimension(getIcon().getIconWidth(), getIcon().getIconHeight()));
        setToolTipText(part.getDescription());
    }

    public PartComponent(Part part) {
        this(part, -1, -1);
    }

    public Part getPartModel() {
        return part;
    }
}
