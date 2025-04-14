package main.java.editormvc.model.productModel;

import application.controller.distrib.ProductController;
import main.java.editormvc.model.productModel.product.Product;
import main.java.editormvc.model.productModel.product.beverage.sugar.all.Coffee;
import main.java.editormvc.model.productModel.product.beverage.sugar.all.chocolate.HotChocolate;

import java.util.ArrayList;

public class ProductsModel {

    private ProductController productController;

    private final ArrayList<Product> products;

    public ProductsModel(ProductController controller){
        this.productController = controller;
        this.products = new ArrayList<>();

        initAllProducts();

    }

    private void initAllProducts(){
        initAllCoffees();

    }


    public ProductController getProductController() {
        return productController;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void initAllCoffees(){
        //---------COFFEE
        Coffee expresso = new Coffee("Expresso");
        expresso.addOneQuantityPrice(30,0.50);
        expresso.addOneQuantityPrice(60,0.90);
        this.products.add(expresso);

        Coffee ristretto = new Coffee("Ristretto");
        ristretto.addOneQuantityPrice(20,0.40);
        this.products.add(ristretto);

        Coffee lungo = new Coffee("Lungo");
        lungo.addOneQuantityPrice(220,0.80);
        lungo.addOneQuantityPrice(260,1.0);
        lungo.addOneQuantityPrice(320,1.30);
        this.products.add(lungo);

        Coffee latte = new Coffee("Latte");
        latte.addOneQuantityPrice(200,1.00);
        latte.addOneQuantityPrice(260,1.40);
        latte.addOneQuantityPrice(300,1.60);
        this.products.add(latte);

        Coffee cappuccino = new Coffee("Cappuccino");
        cappuccino.addOneQuantityPrice(150,1.20);
        cappuccino.addOneQuantityPrice(100,0.90);
        cappuccino.addOneQuantityPrice(200,1.50);
        this.products.add(cappuccino);

        Coffee noisette = new Coffee("Noisette");
        noisette.addOneQuantityPrice(220,1.0);
        noisette.addOneQuantityPrice(260,1.40);
        noisette.addOneQuantityPrice(320,1.60);
        this.products.add(noisette);

        Coffee caramel = new Coffee("Caramel");
        caramel.addOneQuantityPrice(220,1.0);
        caramel.addOneQuantityPrice(260,1.40);
        caramel.addOneQuantityPrice(320,1.60);
        this.products.add(caramel);

        Coffee latteMacchiato = new Coffee("Latte Macchiato");
        latteMacchiato.addOneQuantityPrice(200,1.0);
        latteMacchiato.addOneQuantityPrice(220,1.40);
        latteMacchiato.addOneQuantityPrice(260,1.70);
        this.products.add(latteMacchiato);

        Coffee vienneseCoffee = new Coffee("Viennese coffee");
        vienneseCoffee.addOneQuantityPrice(60,1.0);
        vienneseCoffee.addOneQuantityPrice(80,1.30);
        vienneseCoffee.addOneQuantityPrice(120,1.60);
        this.products.add(vienneseCoffee);

        Coffee irishCoffee = new Coffee("Irish coffee");
        irishCoffee.addOneQuantityPrice(120,1.80);
        irishCoffee.addOneQuantityPrice(150,2.20);
        irishCoffee.addOneQuantityPrice(170,2.50);
        this.products.add(irishCoffee);
    }

    public void initAllHotChocolate(){
        //---------HOT_CHOCOLATE
        HotChocolate hotChocolate;

    }
}
