package de.marskuh.qtp.view;

import de.marskuh.qtp.UserContext;
import de.marskuh.qtp.model.Part;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlanView extends JPanel {

    private PlanController planController = new PlanController();

    private static class PlanController {

        // TODO establish selection model
        private PartComponent currentSelection;

        public void visit(final PlanView planView) {
            planView.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Part part = UserContext.getInstance().getSelectedPart();
                    if (e.getClickCount() >= 2 && part != null) {
                        System.out.println("Dropped part at coordinates x=" + e.getX() + ", y=" + e.getY());
                        final PartComponent partComponent = new PartComponent(part);
                        planView.add(partComponent);
                        planView.validate();

                        // TODO add to parts in UserContext
                        partComponent.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                unselect(currentSelection);
                                select(partComponent);
                                currentSelection = partComponent;
                            }
                        });
                    }
                }
            });
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


//}


    public PlanView() {
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(600, 400));
        planController.visit(this);
//        setMinimumSize(new Dimension(600,400));
    }
}
