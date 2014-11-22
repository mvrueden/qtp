package de.marskuh.qtp.model;

public class Part {

    private String image;
    private int width;
    private int height;
    private String description = "TODO Dummy Description";

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public String getDescription() {
        return description;
    }
}
