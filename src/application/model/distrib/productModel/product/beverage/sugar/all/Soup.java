package application.model.distrib.productModel.product.beverage.sugar.all;

import application.model.distrib.productModel.product.NeedPreparation;
import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;

public class Soup extends CanAddSugar {
    public Soup(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return 0.0;
    }

}
