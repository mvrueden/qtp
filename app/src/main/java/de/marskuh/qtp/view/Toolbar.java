package de.marskuh.qtp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Toolbar extends JToolBar {

    public Toolbar() {
        setBackground(Color.PINK);
        setFloatable(false);

        JButton closeButton = new JButton("Beenden");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame rootWindow = (JFrame) SwingUtilities.getWindowAncestor(Toolbar.this);
                rootWindow.dispatchEvent(new WindowEvent(rootWindow, WindowEvent.WINDOW_CLOSING));
            }
        });
        add(closeButton);
    }
}
