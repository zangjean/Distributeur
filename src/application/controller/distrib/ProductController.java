package application.controller.distrib;

import application.model.distrib.productModel.ProductsModel;
import application.model.distrib.productModel.product.Product;

import java.util.ArrayList;

public class ProductController {
    private ProductsModel productsModel;

    public ProductController() {
        this.productsModel = new ProductsModel(this);
    }

    public ProductsModel getProductsModel() {
        return productsModel;
    }

    public Product createChocolate(String size, String base, String aroma, ArrayList<String> toppings) {
        return productsModel.createProduct("CHOCOLATE",size,base,aroma,toppings);
    }

    public void printAllProduct() {
        this.productsModel.printAllProducts();
    }
}
