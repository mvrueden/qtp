package de.marskuh.qtp.view;

import de.marskuh.qtp.ApplicationContext;
import de.marskuh.qtp.ImageManager;
import de.marskuh.qtp.UserContext;
import de.marskuh.qtp.model.Part;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


// TODO fix flickering while dragging. This might help: http://stackoverflow.com/questions/1033703/swing-how-can-i-prevent-flickering-and-vibrating-of-a-component-when-restric
// TODO remove unused imports
public class PlanView extends JPanel {

    private PlanController planController = new PlanController();

    private static class PlanController {

        // TODO establish selection model
        private PartComponent currentSelection;

        private ImageManager imageManager = ApplicationContext.getInstance().getImageManager();

        private boolean moving;

        public void visit(final PlanView planView) {
            planView.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Part part = UserContext.getInstance().getSelectedPart();
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

                            }

                            @Override
                            public void mouseReleased(MouseEvent e) {
                                Image selectedImage = imageManager.getImage(partComponent.getPartModel().getImage());
                                partComponent.setIcon(new ImageIcon(selectedImage));
                            }
                        });

                        partComponent.addMouseMotionListener(new MouseMotionAdapter() {
                            @Override
                            public void mouseDragged(MouseEvent e) {
                                if (!moving) {
                                    moving = true;
                                    System.out.println("Move component to " + e.getPoint());
                                    move(partComponent, e.getPoint());
                                    moving = false;
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

        //    addKeyListener(new KeyAdapter() {
//        @Override
//        public void keyTyped(KeyEvent e) {
//            remove(currentSelection);
//            select(null);
//        }
//    });

        private void unselect(PartComponent component) {
            if (component != null) {
                component.setBorder(null);
            }
        }

        private void select(PartComponent component) {
            if (component != null) {
                component.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        private void remove(JLabel component) {
            if (component != null) {
//                getContentPane().remove(component);
//                repaint();
            }
        }
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D)g;
//        g2.setColor(Color.BLUE);
//        g2.fillRect(0, 0, getWidth(), getHeight());
//
//        for (Component eachComponent : getComponents()) {
//            if (eachComponent instanceof PartComponent) {
//                PartComponent eachPartComponent = (PartComponent)eachComponent;
//                eachPartComponent.getIcon().paintIcon(this, g2, eachPartComponent.getX(), eachPartComponent.getY());
//
//                if (planController.currentSelection == eachPartComponent) {
//                    g2.drawRect(eachPartComponent.getX(), eachPartComponent.getY(), eachPartComponent.getWidth(), eachPartComponent.getHeight());
//                }
//            }
//        }
//    }

    public PlanView() {
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(600, 400));
        setLayout(null);
        planController.visit(this);
    }
}
