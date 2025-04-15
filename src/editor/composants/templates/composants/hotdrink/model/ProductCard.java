package editor.composants.templates.composants.hotdrink.model;

import java.util.HashSet;
import java.util.Set;

public class ProductCard {
    private String name;
    private Set<Alergene> alergies;
    private double price;
    private String imagePath;
    //private boolean isPopular;

    public ProductCard(String name, double price, String imagePath) {
        this.name = name;
        this.alergies = new HashSet<>();
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

    public void setAlergies(Set<Alergene> alergies) {
        this.alergies = alergies;
    }

    public void addAlergie(Alergene alergie) {
        this.alergies.add(alergie);
    }

    public void removeAlergie(Alergene alergie) {
        this.alergies.remove(alergie);
    }

    // using StringBuilder
    public String AlergenToStringV1() {
        StringBuilder sb = new StringBuilder();
        for (Alergene alergie : this.alergies) {
            sb.append(alergie.toString()).append(", ");
        }

        // Remove the last comma and space
        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 2);
        }


        return sb.toString();
    }

    // using Stream API
    public String AlergenToStringV2()
    {
        return this.alergies.stream()
                .map(Alergene::toString) // Convert each Alergene to String
                .reduce((a, b) -> a + ", " + b) // Reduce to a single String
                .orElse(""); // Return empty String if no Alergene
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
