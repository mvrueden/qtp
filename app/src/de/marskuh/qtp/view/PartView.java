package de.marskuh.qtp.view;

import de.marskuh.qtp.ApplicationContext;
import de.marskuh.qtp.UserContext;
import de.marskuh.qtp.model.Part;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PartView extends JPanel {

    private static interface PartViewModel {
        java.util.List<Part> getParts();
    }

    private static class DefaultPartViewModel implements PartViewModel {
        @Override
        public java.util.List<Part> getParts() {
            return ApplicationContext.getInstance().getPartManager().getParts();
        }
    }

    private static class PartController {

        private PartComponent selectedComponent;

        public void visit(final PartComponent partComponent) {
            partComponent.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    unselect(selectedComponent);
                    select(partComponent);
                    selectedComponent = partComponent;
                    UserContext.getInstance().setSelectedPart(partComponent.getPartModel());
                }
            });
        }

        private void select(PartComponent partComponent) {
            if (partComponent != null) {
                partComponent.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }

        private void unselect(PartComponent partComponent) {
            if (partComponent != null) {
                partComponent.setBorder(null);
            }
        }
    }

    private PartViewModel partViewModel = new DefaultPartViewModel();

    private PartController partController = new PartController();

    public PartView() {
        setBackground(Color.YELLOW);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "DUMMY"));

        for (Part eachPart : partViewModel.getParts()) {
            PartComponent partComponent = new PartComponent(eachPart);
            partController.visit(partComponent);
            add(partComponent);
        }
    }
}
