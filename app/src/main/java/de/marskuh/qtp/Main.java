package de.marskuh.qtp;

import de.marskuh.qtp.view.MainFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = ApplicationContext.getInstance();

        final JFrame app = new MainFrame(applicationContext.getImageManager());

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                app.setVisible(true);
            }
        });
    }
}