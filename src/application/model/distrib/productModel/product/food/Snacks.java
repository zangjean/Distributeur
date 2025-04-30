package application.model.distrib.productModel.product.food;

import application.model.distrib.productModel.product.Product;

import java.util.ArrayList;

public class Snacks extends Product {

    private ArrayList<String> ingredients;
    private int preparationTime; 

    public Snacks(String name) {
        super(name);
        this.ingredients = new ArrayList<>();
        this.preparationTime = 0;
    }

    public Snacks(String name, int preparationTime) {
        super(name);
        this.ingredients = new ArrayList<>();
        this.preparationTime = preparationTime;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String ingredient) {
        this.ingredients.add(ingredient);
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        if (!this.getQuantityPriceMap().isEmpty()) {
            for (var entry : this.getQuantityPriceMap().entrySet()) {
                string.append("Taille (code): ").append(entry.getKey()).append(", Prix €: ").append(entry.getValue()).append("\n");
            }
        } else {
            string.append("");
        }

        return "\n--------" + this.getClass().getSimpleName() + "-------\n" +
                "Name: " + this.getName() + "\n" +
                "Nutriscore : " + this.getNutriscore() + "\n" +
                "Food Allergen : " + this.getFoodAllergen() + "\n" +
                "Preparation Time (min): " + this.preparationTime + "\n" +
                "Ingredients: " + this.ingredients + "\n" +
                string.toString();
    }
}
