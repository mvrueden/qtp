package de.marskuh.qtp;

import de.marskuh.qtp.model.Part;

import java.util.ArrayList;
import java.util.List;

public class PartManager {

    private List<Part> parts = new ArrayList<>();

    public PartManager() {
        parts.add(createPart("gate-right.gif", 279, 166));
        parts.add(createPart("gate-left.gif", 279, 166));
        parts.add(createPart("straight.gif", 136, 64));
        parts.add(createPart("curve.gif", 139, 86));
    }

    // TODO separate track data and track view (e.g. load all parts while starting up)
    private static Part createPart(String name, int width, int height) {
        Part part = new Part();
        part.setImage("/images/parts/" + name);
        part.setWidth(width);
        part.setHeight(64);
        return part;
    }

    public List<Part> getParts() {
        return parts;
    }
}
