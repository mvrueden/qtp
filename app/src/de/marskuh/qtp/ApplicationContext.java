package de.marskuh.qtp;

// Dies sollte später durch google guice ersetzt werden :)
public class ApplicationContext {

    private static ApplicationContext instance;

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    private final PartManager partManager;

    private final ImageManager imageManager;

    private ApplicationContext() {
        partManager = new PartManager();
        imageManager = new ImageManager();
    }

    public ImageManager getImageManager() {
        return imageManager;
    }

    public PartManager getPartManager() {
        return partManager;
    }
}
