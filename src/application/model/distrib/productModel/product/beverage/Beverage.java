package main.java.editormvc.model.productModel.product.beverage;

import main.java.editormvc.model.productModel.product.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class Beverage extends Product {
    //private int quantity ; // en ml
    public Beverage(String name) {
        super(name);
    }

    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        if (!this.getQuantityPriceMap().isEmpty()){
            for (Map.Entry<Integer, Double> entry : this.getQuantityPriceMap().entrySet()) {
                string.append("Quantity (ml): ").append(entry.getKey()).append(", Price €: ").append(entry.getValue()).append("\n");
                //string.append("Preparation Time : ").append(this.getPre)
            }
        }else {
            string.append("");
        }

        return "\n--------"+this.getClass().getSimpleName()+"-------\n" +
                "Name: " + this.getName() +"\n"+
                "Nutriscore : " + this.getNutriscore() +"\n"+
                "Food Allergen : " + this.getFoodAllergen()+ "\n"+
                string.toString()
                ;
    }


}
