package application.model.distrib.productModel.product.beverage.sugar.all;

import application.model.distrib.productModel.product.NeedPreparation;
import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;


public class Coffee extends CanAddSugar implements NeedPreparation {

    public Coffee(String name) {
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
            System.out.println("ERROR : getPreparationTime Coffee -> la quantité n'existe pas");
            return 0;
        }
    }






}
