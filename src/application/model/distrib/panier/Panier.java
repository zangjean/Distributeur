package application.model.distrib.panier;

import application.model.distrib.productModel.product.Product;
import application.model.distrib.saveFav.FavoriteProductForSaveManager;
import application.model.user.User;

import java.util.ArrayList;

/**
 * Classe représentant un panier contenant des produits sélectionnés par l'utilisateur.
 * Permet d'ajouter, retirer, vider le panier, calculer le total, et gérer les produits favoris.
 */
public class Panier {

    /** Liste des produits ajoutés dans le panier. */
    private ArrayList<ProductForPanier> products;

    /** Utilisateur propriétaire du panier. */
    private User user;

    /** Gestionnaire des produits favoris pour sauvegarde et analyse. */
    private FavoriteProductForSaveManager favoriteProductForSaveManager;

    /**
     * Constructeur par défaut.
     * Initialise un panier vide et le gestionnaire de favoris.
     */
    public Panier() {
        products = new ArrayList<>();
        user = null;
        favoriteProductForSaveManager = new FavoriteProductForSaveManager();
    }

    /**
     * Retourne la liste des produits du panier.
     *
     * @return Liste des {@link ProductForPanier}.
     */
    public ArrayList<ProductForPanier> getProducts() {
        return products;
    }

    /**
     * Ajoute un produit au panier.
     * Si le produit existe déjà avec la même taille, incrémente la quantité demandée.
     * Sinon, ajoute un nouveau produit.
     *
     * @param product  Produit à ajouter.
     * @param quantity Quantité (en ml).
     * @param price    Prix unitaire du produit.
     */
    public void addProduct(Product product, int quantity, double price) {
        boolean inProducts = false;

        for (ProductForPanier productForPanier : products) {
            if (product.getName().equals(productForPanier.getNameProduct())) {
                if (productForPanier.getQuantityML() == quantity) {
                    productForPanier.setAskingQuantity(productForPanier.getAskingQuantity() + 1);
                    inProducts = true;
                    break;
                }
            }
        }

        if (!inProducts) {
            ProductForPanier productForPanier = new ProductForPanier(product.getName(), quantity, price);
            productForPanier.setOriginaClassName(product.getClass().getSimpleName());
            products.add(productForPanier);
        }
    }

    /**
     * Supprime un produit du panier.
     *
     * @param product Le produit à supprimer.
     */
    public void removeProduct(ProductForPanier product) {
        products.remove(product);
    }

    /**
     * Vide complètement le panier.
     */
    public void clear() {
        this.products = new ArrayList<>();
    }

    /**
     * Retourne l'utilisateur propriétaire du panier.
     *
     * @return L'utilisateur connecté, ou {@code null}.
     */
    public User getUser() {
        return user;
    }

    /**
     * Définit l'utilisateur propriétaire du panier.
     *
     * @param user L'utilisateur connecté.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Met à jour les produits favoris à partir du contenu actuel du panier.
     *
     * @param panier Panier utilisé pour mettre à jour les favoris.
     */
    public void updateFavorite(Panier panier) {
        this.favoriteProductForSaveManager.run(panier);
    }

    /**
     * Retourne les noms des trois produits les plus fréquemment achetés.
     *
     * @return Liste des noms des produits favoris.
     */
    public ArrayList<String> return3FavoriteProduct() {
        return this.favoriteProductForSaveManager.return3MostFavProd();
    }

    /**
     * Calcule les points fidélité gagnés selon les produits ajoutés.
     * (10 points par euro dépensé)
     *
     * @return Nombre total de points gagnés.
     */
    public int howManyPointsUserWin() {
        int points = 0;
        for (ProductForPanier productForPanier : products) {
            points += (int) (productForPanier.getPrice() * 10.0);
        }
        return points;
    }

    /**
     * Ajoute les points fidélité à l'utilisateur connecté.
     *
     * @param points Points à créditer.
     */
    public void setPointsForCurrentUser(int points) {
        this.user.addPoints(points);
    }

    /**
     * Calcule le prix total du panier en fonction de la quantité demandée pour chaque produit.
     *
     * @return Prix total en euros.
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        for (ProductForPanier productForPanier : products) {
            totalPrice += productForPanier.getPrice() * productForPanier.getAskingQuantity();
        }
        return totalPrice;
    }
}
