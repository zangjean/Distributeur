package application.model.distrib.productModel;

import application.controller.distrib.ProductController;
import application.model.distrib.productModel.product.Product;
import application.model.distrib.productModel.product.beverage.sugar.all.Coffee;
import application.model.distrib.productModel.product.beverage.sugar.all.chocolate.Aroma;
import application.model.distrib.productModel.product.beverage.sugar.all.chocolate.HotChocolate;
import application.model.distrib.productModel.product.beverage.sugar.all.chocolate.Toping;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class ProductsModel {

    private ProductController productController;

    private ArrayList<Product> products;

    //VANILLA,HAZELNUT,CARAMEL,ALMOND
    private Map<String, Aroma> aromaMap = Map.of(
            "VANILLA", Aroma.VANILLA,
            "HAZELNUT", Aroma.HAZELNUT,
            "CARAMEL", Aroma.CARAMEL,
            "ALMOND", Aroma.ALMOND
    );

    //MARSHMALLOW,CHANTILLY,SPECULOS,OREO
    private Map<String, Toping> toppingMap = Map.of(
            "MARSHMALLOW", Toping.MARSHMALLOW,
            "CHANTILLY", Toping.CHANTILLY,
            "SPECULOS", Toping.SPECULOS,
            "OREO", Toping.OREO
    );


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

        Coffee latteMacchiato = new Coffee("Macchiato");
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

    public Product createProduct(String type, String size, String base, String aroma, ArrayList<String> toppings) {
        Product product = null;
        if(type.equals("CHOCOLATE")){
            String name =size+ " Hot Chocolate with "+base;
            String aromaString = " and "+aroma;
            String topingsString = " topped by ";


            HotChocolate chocolate = new HotChocolate("");


            if(base.equals("MILK")){
                chocolate.setWithMilk(true);
            }else{
                chocolate.setWithMilk(false);
            }

            if (this.aromaMap.containsKey(aroma)) {
                chocolate.setAroma(this.aromaMap.get(aroma));
                name = name + aromaString;
            }

            if(!toppings.isEmpty()){
                HashSet<Toping> toppingHashSet = new HashSet<>();
                for (String topping : toppings) {
                    if(this.toppingMap.containsKey(topping)){
                        toppingHashSet.add(this.toppingMap.get(topping));
                        topingsString = topingsString+", " + topping ;
                    }
                }
                name = name + topingsString;
                chocolate.setTopings(toppingHashSet);
            }
            chocolate.setName(name);
            product = chocolate;
            chocolate.addOneQuantityPrice(chocolate.getSize(size),1.20+chocolate.getPrice());
            //this.products.add(chocolate);

        }

        return product;
    }


    public void printAllProducts(){
        System.out.println("Liste des produits : ");
        for (Product product:products){
            product.toString();
        }
    }
}
