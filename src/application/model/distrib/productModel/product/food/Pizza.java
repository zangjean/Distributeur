package application.model.distrib.productModel.product.food;

import application.model.distrib.productModel.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pizza extends Product {

    private ArrayList<String> toppings;
    private int preparationTime; 
    private Map<Integer, Double> sizePriceMap;

    public Pizza(String name) {
        super(name);
        this.toppings = new ArrayList<>();
        this.preparationTime = 0;
        this.sizePriceMap = new HashMap<>();
    }

    public Pizza(String name, int preparationTime) {
        super(name);
        this.toppings = new ArrayList<>();
        this.preparationTime = preparationTime;
        this.sizePriceMap = new HashMap<>();
    }

    public ArrayList<String> getToppings() {
        return toppings;
    }

    public void addTopping(String topping) {
        this.toppings.add(topping);
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public void addSizePrice(int size, double price) {
        sizePriceMap.put(size, price);
    }

    public Map<Integer, Double> getSizePriceMap() {
        return sizePriceMap;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        if (!this.sizePriceMap.isEmpty()) {
            for (var entry : this.sizePriceMap.entrySet()) {
                string.append("Taille (code): ").append(entry.getKey()).append(", Prix €: ").append(entry.getValue()).append("\n");
            }
        } else if (!this.getQuantityPriceMap().isEmpty()) {
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
                "Toppings: " + this.toppings + "\n" +
                string.toString();
    }
}
