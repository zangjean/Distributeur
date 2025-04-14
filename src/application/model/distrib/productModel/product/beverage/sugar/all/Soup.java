package main.java.editormvc.model.productModel.product.beverage.sugar.all;

import main.java.editormvc.model.productModel.product.NeedPreparation;
import main.java.editormvc.model.productModel.product.beverage.sugar.CanAddSugar;

public class Soup extends CanAddSugar implements NeedPreparation {
    public Soup(String name) {
        super(name);
    }

    @Override
    public int getPrice() {
        return 0;
    }

    @Override
    public int getPreparationTime(Integer quantity) {
        if(this.getQuantityPriceMap().containsKey(quantity)){
            return quantity / 100 * 15;
        }else {
            System.out.println("ERROR : getPreparationTime Soup -> la quantité n'existe pas");
            return 0;
        }
    }
}
