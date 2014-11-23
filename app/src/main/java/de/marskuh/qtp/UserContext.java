package de.marskuh.qtp;

import de.marskuh.qtp.model.Part;

import java.util.ArrayList;
import java.util.List;

public class UserContext {

    private static UserContext instance;

    public static UserContext getInstance() {
        if (instance == null) {
            instance = new UserContext();
        }
        return instance;
    }

    private Part selectedPart;

    private List<Part> parts = new ArrayList<>();

    private UserContext() {

    }

    public void setSelectedPart(Part selectedPart) {
        this.selectedPart = selectedPart;
    }

    public Part getSelectedPart() {
        return selectedPart;
    }
}
