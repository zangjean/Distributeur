package application.model.distrib.productModel;

import application.controller.distrib.ProductController;
import application.model.distrib.productModel.product.Product;
import application.model.distrib.productModel.product.beverage.Soda;
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
        initAllSodas();
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
    private void initAllCoffees() {
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

    private void initAllSodas() {
        //
        Soda cocacola = new Soda("Coca-Cola");
        cocacola.addOneQuantityPrice(330, 1.50);
        cocacola.addOneQuantityPrice(500, 2.20);
        this.products.add(cocacola);

        Soda cocacolaZero = new Soda("Coca-Cola Zero");
        cocacolaZero.addOneQuantityPrice(500, 2.00);
        this.products.add(cocacolaZero);

        Soda fantaOrange = new Soda("Fanta Orange");
        fantaOrange.addOneQuantityPrice(330, 1.50);
        fantaOrange.addOneQuantityPrice(500, 2.20);
        this.products.add(fantaOrange);

        Soda fantaCitron = new Soda("Fanta Citron");
        fantaCitron.addOneQuantityPrice(500, 2.00);
        this.products.add(fantaCitron);

        Soda pepsi = new Soda("Pepsi");
        pepsi.addOneQuantityPrice(330, 1.40);
        this.products.add(pepsi);

        Soda pepsiMax = new Soda("Pepsi Max");
        pepsiMax.addOneQuantityPrice(500, 1.90);
        this.products.add(pepsiMax);

        Soda iceTeaPeche = new Soda("Lipton Ice Tea Peche");
        iceTeaPeche.addOneQuantityPrice(330, 1.60);
        iceTeaPeche.addOneQuantityPrice(500, 2.10);
        this.products.add(iceTeaPeche);

        Soda iceTeaCitron = new Soda("Lipton Ice Tea Citron");
        iceTeaCitron.addOneQuantityPrice(500, 2.00);
        this.products.add(iceTeaCitron);

        Soda oasisTropical = new Soda("Oasis Tropical");
        oasisTropical.addOneQuantityPrice(330, 1.60);
        oasisTropical.addOneQuantityPrice(500, 2.10);
        this.products.add(oasisTropical);

        Soda oasisPommeCassis = new Soda("Oasis Pomme Cassis Framboise");
        oasisPommeCassis.addOneQuantityPrice(500, 2.10);
        this.products.add(oasisPommeCassis);

        Soda minuteMaidOrange = new Soda("Minute Maid Orange");
        minuteMaidOrange.addOneQuantityPrice(330, 1.80);
        minuteMaidOrange.addOneQuantityPrice(500, 2.30);
        this.products.add(minuteMaidOrange);

        Soda minuteMaidPomme = new Soda("Minute Maid Pomme");
        minuteMaidPomme.addOneQuantityPrice(500, 2.10);
        this.products.add(minuteMaidPomme);

        Soda tropicanaOrange = new Soda("Tropicana Orange");
        tropicanaOrange.addOneQuantityPrice(330, 2.00);
        this.products.add(tropicanaOrange);

        Soda pulcoCitronnade = new Soda("Pulco Citronnade");
        pulcoCitronnade.addOneQuantityPrice(500, 1.90);
        this.products.add(pulcoCitronnade);

        Soda sanPellegrino = new Soda("San Pellegrino");
        sanPellegrino.addOneQuantityPrice(500, 2.00);
        this.products.add(sanPellegrino);

        Soda evian = new Soda("Evian");
        evian.addOneQuantityPrice(500, 1.20);
        evian.addOneQuantityPrice(750, 1.80);
        this.products.add(evian);

        Soda vittel = new Soda("Vittel");
        vittel.addOneQuantityPrice(500, 1.20);
        this.products.add(vittel);

        Soda cristaline = new Soda("Cristaline");
        cristaline.addOneQuantityPrice(500, 1.00);
        cristaline.addOneQuantityPrice(330, 1.00);
        this.products.add(cristaline);

        Soda perrier = new Soda("Perrier");
        perrier.addOneQuantityPrice(500, 1.50);
        this.products.add(perrier);

        Soda badoit = new Soda("Badoit");
        badoit.addOneQuantityPrice(500, 1.60);
        this.products.add(badoit);

        Soda redBull = new Soda("Red Bull");
        redBull.addOneQuantityPrice(250, 2.20);
        this.products.add(redBull);

        Soda redBullSugarfree = new Soda("Red Bull Sugarfree");
        redBullSugarfree.addOneQuantityPrice(250, 2.20);
        this.products.add(redBullSugarfree);

        Soda monsterEnergy = new Soda("Monster Energy");
        monsterEnergy.addOneQuantityPrice(500, 2.50);
        this.products.add(monsterEnergy);

        Soda monsterUltra = new Soda("Monster Ultra");
        monsterUltra.addOneQuantityPrice(500, 2.50);
        this.products.add(monsterUltra);

        Soda gatorade = new Soda("Gatorade Citron");
        gatorade.addOneQuantityPrice(500, 2.30);
        this.products.add(gatorade);

        Soda powerade = new Soda("Powerade Fruits Rouges");
        powerade.addOneQuantityPrice(500, 2.30);
        this.products.add(powerade);

        Soda arizona = new Soda("Arizona The Vert");
        arizona.addOneQuantityPrice(500, 2.20);
        this.products.add(arizona);

        Soda snapple = new Soda("Snapple Peche");
        snapple.addOneQuantityPrice(500, 2.30);
        this.products.add(snapple);

    }

    public ArrayList<Soda> getSodas() {
        ArrayList<Soda> sodas = new ArrayList<>();
        for (Product product : products) {
            if (product instanceof Soda) {
                sodas.add((Soda) product);
            }
        }
        return sodas;
    }

    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getName().toLowerCase().equals(name)) {
                return product;
            }
        }
        return null;
    }
}
