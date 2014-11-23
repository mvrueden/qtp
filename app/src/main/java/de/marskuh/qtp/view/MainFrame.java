package de.marskuh.qtp.view;

import de.marskuh.qtp.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        init();
        pack();
        setLocationRelativeTo(null); // center
    }

    private void init() throws IOException {
        JSplitPane mainPanel = new JSplitPane();
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setDividerLocation(0.75);
        mainPanel.setResizeWeight(1.0);

        planView = new PlanView();
        partView = new PartView();
        toolbar = new Toolbar();

        mainPanel.setLeftComponent(planView);
        mainPanel.setRightComponent(partView);

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(toolbar, BorderLayout.NORTH);
        add(footerView, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this, "Möchtest du die Anwendung wirklich beenden?", "Wirklich beenden?", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    MainFrame.this.dispose();
                    System.exit(0);
                }
            }
        });
    }
}
