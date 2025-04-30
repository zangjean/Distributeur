package application.model.distrib.productModel.product.beverage.sugar.all;

import application.model.distrib.productModel.product.NeedPreparation;
import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;

public class Tea extends CanAddSugar implements NeedPreparation {
    public Tea(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public int getPreparationTime(Integer quantity) {
        if(this.getQuantityPriceMap().containsKey(quantity)){
            return quantity / 100 * 15;
        }else {
            System.out.println("ERROR : getPreparationTime Tea -> la quantité n'existe pas");
            return 0;
        }
    }
}
