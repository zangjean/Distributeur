package application.model.distrib.productModel.product.beverage.sugar.all;

import application.model.distrib.productModel.product.NeedPreparation;
import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;

public class Soup extends CanAddSugar implements NeedPreparation {
    public Soup(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return 0.0;
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
