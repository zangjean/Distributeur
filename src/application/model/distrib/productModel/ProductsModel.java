package application.model.distrib.productModel;

import application.controller.distrib.ProductController;
import application.model.distrib.productModel.product.Product;
import application.model.distrib.productModel.product.beverage.sugar.all.Coffee;
import application.model.distrib.productModel.product.beverage.sugar.all.chocolate.HotChocolate;
import application.model.distrib.productModel.product.food.Pizza;
import application.model.distrib.productModel.product.food.Snacks;

import java.util.ArrayList;

public class ProductsModel {

    private ProductController productController;

    private ArrayList<Product> products;

    public ProductsModel(ProductController controller){
        this.productController = controller;
        this.products = new ArrayList<>();

        initAllProducts();

    }

    private void initAllProducts(){
        initAllCoffees();
        initAllPizzas();
        initAllSnacks();
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
        ristretto.addOneQuantityPrice(40,0.70);
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
        HotChocolate hotChocolate = new HotChocolate("");

    }

    public void initAllPizzas(){
        Pizza margherita = new Pizza("Margherita", 15);
        margherita.addOneQuantityPrice(1, 5.00);
        margherita.addOneQuantityPrice(2, 7.50);
        this.products.add(margherita);

        Pizza pepperoni = new Pizza("Pepperoni", 20);
        pepperoni.addOneQuantityPrice(1, 6.00);
        pepperoni.addOneQuantityPrice(2, 8.50);
        this.products.add(pepperoni);

        Pizza hawaiian = new Pizza("Hawaiian", 18);
        hawaiian.addOneQuantityPrice(1, 6.50);
        hawaiian.addOneQuantityPrice(2, 9.00);
        this.products.add(hawaiian);
    }

    public void initAllSnacks(){
        Snacks chips = new Snacks("Chips", 5);
        chips.addOneQuantityPrice(1, 2.00);
        chips.addOneQuantityPrice(2, 3.50);
        this.products.add(chips);

        Snacks nuts = new Snacks("Nuts", 5);
        nuts.addOneQuantityPrice(1, 2.50);
        nuts.addOneQuantityPrice(2, 4.00);
        this.products.add(nuts);

        Snacks cookies = new Snacks("Cookies", 10);
        cookies.addOneQuantityPrice(1, 3.00);
        cookies.addOneQuantityPrice(2, 5.00);
        this.products.add(cookies);
    }

    public ArrayList<Product> getProductsByName(String name){
        ArrayList<Product> productsByName = new ArrayList<>();
        for (Product product : products) {
            if(product.getName().equals(name)){
                productsByName.add(product);
            }
        }
        return productsByName;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void removeProduct(Product product){
        this.products.remove(product);
    }

    public ArrayList<Product> getAllProducts(){
        return this.products;
    }

    public ProductController getProductController(String name){
        return this.productController;
    }
}
