package application.controller.distrib;

import application.model.distrib.productModel.ProductsModel;
import application.model.distrib.productModel.product.Product;

import java.util.ArrayList;

/**
 * Contrôleur responsable de la gestion des produits.
 * Interagit avec le modèle {@link ProductsModel} pour créer et récupérer des produits.
 */
public class ProductController {

    /** Modèle contenant la logique et les données des produits. */
    private ProductsModel productsModel;

    /**
     * Constructeur par défaut.
     * Initialise le modèle de produits en lui passant une référence vers ce contrôleur.
     */
    public ProductController() {
        this.productsModel = new ProductsModel(this);
    }

    /**
     * Retourne le modèle des produits.
     *
     * @return L'instance de {@link ProductsModel}.
     */
    public ProductsModel getProductsModel() {
        return productsModel;
    }

    /**
     * Crée un produit de type chocolat personnalisé avec les paramètres spécifiés.
     *
     * @param size     Taille du produit (ex: SMALL, MEDIUM, LARGE).
     * @param base     Base liquide (ex: MILK, WATER).
     * @param aroma    Arôme sélectionné (ex: VANILLA, HAZELNUT).
     * @param toppings Liste des toppings (ex: OREO, MARSHMALLOW).
     * @return Une instance de {@link Product} représentant le chocolat créé.
     */
    public Product createChocolate(String size, String base, String aroma, ArrayList<String> toppings) {
        return productsModel.createProduct("CHOCOLATE", size, base, aroma, toppings);
    }

    /**
     * Affiche la liste de tous les produits disponibles en console.
     * Utile pour le débogage ou l'affichage simple.
     */
    public void printAllProduct() {
        this.productsModel.printAllProducts();
    }
}
