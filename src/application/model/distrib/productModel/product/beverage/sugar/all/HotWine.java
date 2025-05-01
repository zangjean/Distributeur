package application.model.distrib.productModel.product.beverage.sugar.all;

import application.model.distrib.productModel.product.NeedPreparation;
import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;

public class HotWine extends CanAddSugar {

    public HotWine(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        return 0;
    }

}
