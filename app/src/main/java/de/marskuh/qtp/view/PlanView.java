package de.marskuh.qtp.view;

import de.marskuh.qtp.ApplicationContext;
import de.marskuh.qtp.ImageManager;
import de.marskuh.qtp.UserContext;
import de.marskuh.qtp.model.Part;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


// TODO remove unused imports
public class PlanView extends JPanel {

    private PlanController planController = new PlanController();

    private static class PlanController {

        // TODO establish selection model
        private PartComponent currentSelection;

        private ImageManager imageManager = ApplicationContext.getInstance().getImageManager();

        private UserContext userContext = UserContext.getInstance();

        private Point pressedLocation;

        private Point componentLocation;

        public void visit(final PlanView planView) {
            planView.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Part part = userContext.getSelectedPart();
                    if (e.getClickCount() >= 2 && part != null) {
                        System.out.println("Dropped part at coordinates x=" + e.getX() + ", y=" + e.getY());
                        final PartComponent partComponent = new PartComponent(part);
                        move(partComponent, e.getPoint());
                        planView.add(partComponent);
                        planView.repaint();

                        // TODO add to parts in UserContext
                        partComponent.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                unselect(currentSelection);
                                select(partComponent);
                                currentSelection = partComponent;
                            }
                        });

                        partComponent.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                Image selectedImage = imageManager.getImage(partComponent.getPartModel().getImage(), 1.5f);
                                partComponent.setIcon(new ImageIcon(selectedImage));
                                partComponent.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));

                                pressedLocation = e.getLocationOnScreen();
                                componentLocation = partComponent.getLocation();
                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {
                                Image selectedImage = imageManager.getImage(partComponent.getPartModel().getImage());
                                partComponent.setIcon(new ImageIcon(selectedImage));
                                partComponent.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

                                pressedLocation = null;
                                componentLocation = null;
                            }
                        });

                        partComponent.addMouseMotionListener(new MouseMotionAdapter() {
                            @Override
                            public void mouseDragged(MouseEvent e) {
                                Point eventLocation = e.getLocationOnScreen();
                                int dragX = eventLocation.x - pressedLocation.x;
                                int dragY = eventLocation.y - pressedLocation.y;

                                partComponent.setLocation(componentLocation.x + dragX, componentLocation.y + dragY);
                            }
                        });

                        partComponent.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyReleased(KeyEvent e) {
                                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                                    planView.remove(e.getComponent());
                                    planView.repaint();
                                    currentSelection = null;
                                }
                            }
                        });
                    }
                }
            });
        }

        private void move(PartComponent partComponent, Point point) {
            Dimension size = partComponent.getPreferredSize();
            partComponent.setBounds(point.x, point.y, size.width, size.height);
        }

        private void unselect(PartComponent component) {
            if (component != null) {
                component.setBorder(null);
            }
        }

        private void select(PartComponent component) {
            if (component != null) {
                component.setBorder(BorderFactory.createLineBorder(Color.RED));
                component.requestFocusInWindow();
            }
        }
    }

    public PlanView() {
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(600, 400));
        setLayout(null);
        planController.visit(this);
    }
}
