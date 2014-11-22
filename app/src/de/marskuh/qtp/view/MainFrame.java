package de.marskuh.qtp.view;

import de.marskuh.qtp.ImageManager;
import de.marskuh.qtp.Main;
import de.marskuh.qtp.model.Part;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

// TODO we have to check the license of logo.png --> it is from findicons.com
public class MainFrame extends JFrame {

    // TODO this should live in a "ApplicationContext", e.g. SelectionContext, etc.
    private JLabel currentSelection;

    private PartView partView;

    private PlanView planView;

    private Toolbar toolbar;

    private FooterView footerView = new FooterView();

    public MainFrame(ImageManager imageManager) throws IOException {
        setTitle("Q's Train Simulator");
        setIconImage(imageManager.getImage(de.marskuh.qtp.ImageManager.Image.Logo));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        pack();
    }

    private void init() throws IOException {
        JSplitPane mainPanel = new JSplitPane();
        mainPanel.setBackground(Color.CYAN);

        planView = new PlanView();
        partView = new PartView();
        toolbar = new Toolbar();

        mainPanel.setLeftComponent(planView);
        mainPanel.setRightComponent(partView);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);
        add(footerView, BorderLayout.SOUTH);

    }

    private JLabel createTrackLabel(Part part) throws IOException {
        InputStream inputStream = Main.class.getResourceAsStream("/" + part.getImage());
        Objects.requireNonNull(inputStream, "Image " + part.getImage() + " not found.");
        BufferedImage trackImage = ImageIO.read(inputStream);
        JLabel picLabel = new JLabel(new ImageIcon(trackImage));
        return picLabel;
    }
}
