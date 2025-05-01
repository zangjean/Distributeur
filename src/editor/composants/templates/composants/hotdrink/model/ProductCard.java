package editor.composants.templates.composants.hotdrink.model;

import java.io.Serializable;


public class ProductCard implements Serializable {
    private String name;
    private double price;
    private String imagePath;
    //private boolean isPopular;

    public ProductCard(String name, double price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }


}
