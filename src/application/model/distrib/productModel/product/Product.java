package application.model.distrib.productModel.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Product {

    private String name;
    private ArrayList<String> foodAllergen;

    private Map<Integer, Double> quantityPriceMap;
    //for Beverage -> ml
    //for Food     -> (1,small);(2,medium);(3,large)


    private Nutriscore nutriscore;


    public Product(String name)
    {
        this.name = name;
        this.foodAllergen = new ArrayList<>();
        this.quantityPriceMap = new HashMap<>();
    }

    public String getName()
    {
        return name;
    }


    public ArrayList<String> getFoodAllergen()
    {
        return foodAllergen;
    }

    public void setFoodAllergen(ArrayList<String> foodAllergen)
    {
        this.foodAllergen = foodAllergen;
    }

    public void addOneFoodAllergen(String foodAllergen){
        this.foodAllergen.add(foodAllergen);
    }

    public void setNutriscore(String nutriscore) {
        try {
            this.nutriscore = Nutriscore.valueOf(nutriscore);
        } catch (IllegalArgumentException e) {
            System.out.println("Valeur de nutriscore invalide: " + nutriscore);
        }
    }

    public Nutriscore getNutriscore() {
        return nutriscore;
    }

    @Override
    public String toString() {
        return "\n--------Product-------\n" +
                "Name: " + this.name +"\n"+
                "Nutriscore : " + this.nutriscore +"\n"+
                "Food Allergen : " + this.foodAllergen + "\n"
                ;
    }

    public Map<Integer, Double> getQuantityPriceMap() {
        return quantityPriceMap;
    }

    public void setQuantityPriceMap(Map<Integer, Double> quantityPriceMap) {
        this.quantityPriceMap = quantityPriceMap;
    }

    public void addOneQuantityPrice(Integer quantity, Double price){
        this.quantityPriceMap.put(quantity,price);
    }

    public Double getOnePrice(Integer quantity){
        return this.quantityPriceMap.get(quantity);
    }

    public void changeOnePrice(Integer quantity, Double newPrice){
        this.quantityPriceMap.remove(quantity);
        this.quantityPriceMap.put(quantity,newPrice);
    }

    public Map<Integer, Double> getPriceMap(){
        return this.quantityPriceMap;
    }

    public void setName(String name) {
        this.name=name;
    }
}
