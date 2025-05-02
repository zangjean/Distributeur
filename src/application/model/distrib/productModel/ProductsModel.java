package application.model.distrib.productModel;

import application.controller.distrib.ProductController;
import application.model.distrib.productModel.product.Product;
import application.model.distrib.productModel.product.beverage.sugar.all.Coffee;
import application.model.distrib.productModel.product.beverage.sugar.all.chocolate.Aroma;
import application.model.distrib.productModel.product.beverage.sugar.all.chocolate.HotChocolate;
import application.model.distrib.productModel.product.beverage.sugar.all.chocolate.Topping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

/**
 * Modèle central contenant tous les produits proposés dans l'application.
 * Permet d'initialiser, ajouter, rechercher ou générer dynamiquement des produits personnalisés.
 */
public class ProductsModel {

    /** Contrôleur associé pour interagir avec l'interface. */
    private ProductController productController;

    /** Liste de tous les produits disponibles. */
    private ArrayList<Product> products;

    /** Mapping entre nom et arôme pour les chocolats chauds. */
    private Map<String, Aroma> aromaMap = Map.of(
            "VANILLA", Aroma.VANILLA,
            "HAZELNUT", Aroma.HAZELNUT,
            "CARAMEL", Aroma.CARAMEL,
            "ALMOND", Aroma.ALMOND
    );

    /** Mapping entre nom et topping pour les chocolats chauds. */
    private Map<String, Topping> toppingMap = Map.of(
            "MARSHMALLOW", Topping.MARSHMALLOW,
            "CHANTILLY", Topping.CHANTILLY,
            "SPECULOS", Topping.SPECULOS,
            "OREO", Topping.OREO
    );

    /**
     * Constructeur principal du modèle de produits.
     *
     * @param controller Référence vers le contrôleur produit.
     */
    public ProductsModel(ProductController controller) {
        this.productController = controller;
        this.products = new ArrayList<>();
        initAllProducts();
    }

    /**
     * Initialise tous les produits disponibles à l'application.
     */
    private void initAllProducts() {
        initAllCoffees();
    }

    /**
     * Retourne le contrôleur produit associé.
     *
     * @return Contrôleur {@link ProductController}.
     */
    public ProductController getProductController() {
        return productController;
    }

    /**
     * Retourne la liste de tous les produits.
     *
     * @return Liste des {@link Product}.
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    /**
     * Initialise la liste des cafés avec quantités et prix prédéfinis.
     */
    public void initAllCoffees() {
        // Crée plusieurs cafés avec différentes tailles/prix
        Coffee expresso = new Coffee("Expresso");
        expresso.addOneQuantityPrice(30, 0.50);
        expresso.addOneQuantityPrice(60, 0.90);
        this.products.add(expresso);

        Coffee ristretto = new Coffee("Ristretto");
        ristretto.addOneQuantityPrice(20, 0.40);
        ristretto.addOneQuantityPrice(40, 0.70);
        this.products.add(ristretto);

        Coffee lungo = new Coffee("Lungo");
        lungo.addOneQuantityPrice(220, 0.80);
        lungo.addOneQuantityPrice(260, 1.0);
        lungo.addOneQuantityPrice(320, 1.30);
        this.products.add(lungo);

        Coffee latte = new Coffee("Latte");
        latte.addOneQuantityPrice(200, 1.00);
        latte.addOneQuantityPrice(260, 1.40);
        latte.addOneQuantityPrice(300, 1.60);
        this.products.add(latte);

        Coffee cappuccino = new Coffee("Cappuccino");
        cappuccino.addOneQuantityPrice(150, 1.20);
        cappuccino.addOneQuantityPrice(100, 0.90);
        cappuccino.addOneQuantityPrice(200, 1.50);
        this.products.add(cappuccino);

        Coffee noisette = new Coffee("Noisette");
        noisette.addOneQuantityPrice(220, 1.0);
        noisette.addOneQuantityPrice(260, 1.40);
        noisette.addOneQuantityPrice(320, 1.60);
        this.products.add(noisette);

        Coffee caramel = new Coffee("Caramel");
        caramel.addOneQuantityPrice(220, 1.0);
        caramel.addOneQuantityPrice(260, 1.40);
        caramel.addOneQuantityPrice(320, 1.60);
        this.products.add(caramel);

        Coffee latteMacchiato = new Coffee("Macchiato");
        latteMacchiato.addOneQuantityPrice(200, 1.0);
        latteMacchiato.addOneQuantityPrice(220, 1.40);
        latteMacchiato.addOneQuantityPrice(260, 1.70);
        this.products.add(latteMacchiato);

        Coffee vienneseCoffee = new Coffee("Viennese coffee");
        vienneseCoffee.addOneQuantityPrice(60, 1.0);
        vienneseCoffee.addOneQuantityPrice(80, 1.30);
        vienneseCoffee.addOneQuantityPrice(120, 1.60);
        this.products.add(vienneseCoffee);

        Coffee irishCoffee = new Coffee("Irish coffee");
        irishCoffee.addOneQuantityPrice(120, 1.80);
        irishCoffee.addOneQuantityPrice(150, 2.20);
        irishCoffee.addOneQuantityPrice(170, 2.50);
        this.products.add(irishCoffee);
    }


    /**
     * Crée un chocolat chaud personnalisé selon les paramètres.
     *
     * @param type     Type du produit ("CHOCOLATE").
     * @param size     Taille (SMALL, MEDIUM, LARGE).
     * @param base     Base liquide ("MILK", "WATER").
     * @param aroma    Arôme sélectionné (ou "RIEN").
     * @param toppings Liste des toppings à ajouter.
     * @return Le produit chocolat personnalisé.
     */
    public Product createProduct(String type, String size, String base, String aroma, ArrayList<String> toppings) {
        Product product = null;

        if (type.equals("CHOCOLATE")) {
            String name = size + " Hot Chocolate with " + base;
            String aromaString = " and " + aroma;
            String toppingsString = " topped by ";

            HotChocolate chocolate = new HotChocolate("");

            // Base lait ou eau
            chocolate.setWithMilk("MILK".equals(base));

            // Arôme
            if (this.aromaMap.containsKey(aroma)) {
                chocolate.setAroma(this.aromaMap.get(aroma));
                name += aromaString;
            }

            // Toppings
            if (!toppings.isEmpty()) {
                HashSet<Topping> toppingHashSet = new HashSet<>();
                for (String topping : toppings) {
                    if (this.toppingMap.containsKey(topping)) {
                        toppingHashSet.add(this.toppingMap.get(topping));
                        toppingsString += ", " + topping;
                    }
                }
                chocolate.setTopings(toppingHashSet);
                name += toppingsString;
            }

            chocolate.setName(name);
            chocolate.addOneQuantityPrice(chocolate.getSize(size), 1.20 + chocolate.getPrice());
            product = chocolate;
        }

        return product;
    }

    /**
     * Retourne les produits dont le nom correspond à celui passé en paramètre.
     *
     * @param name Nom recherché.
     * @return Liste de produits correspondants.
     */
    public ArrayList<Product> getProductsByName(String name) {
        ArrayList<Product> productsByName = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().equals(name)) {
                productsByName.add(product);
            }
        }
        return productsByName;
    }

    /**
     * Ajoute un produit manuellement à la liste.
     *
     * @param product Produit à ajouter.
     */
    public void addProduct(Product product) {
        this.products.add(product);
    }

    /**
     * Supprime un produit de la liste.
     *
     * @param product Produit à supprimer.
     */
    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    /**
     * Retourne la liste de tous les produits enregistrés.
     *
     * @return Liste des produits.
     */
    public ArrayList<Product> getAllProducts() {
        return this.products;
    }

    /**
     * Retourne le contrôleur produit associé (alias).
     *
     * @param name (non utilisé)
     * @return Instance du {@link ProductController}.
     */
    public ProductController getProductController(String name) {
        return this.productController;
    }

    /**
     * Affiche tous les produits en console.
     */
    public void printAllProducts() {
        System.out.println("Liste des produits : ");
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
}
