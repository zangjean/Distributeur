package application.controller.distrib;

import application.model.distrib.productModel.ProductsModel;

public class ProductController {
    private ProductsModel productsModel;

    public ProductController() {
        this.productsModel = new ProductsModel(this);
    }

    public ProductsModel getProductsModel() {
        return productsModel;
    }

    public void createChocolate(){

    }
}
