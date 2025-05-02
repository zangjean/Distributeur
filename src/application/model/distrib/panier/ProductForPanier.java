package application.model.distrib.panier;

/**
 * Représente un produit ajouté au panier avec ses attributs essentiels :
 * nom, quantité, prix, et nombre d'unités demandées.
 */
public class ProductForPanier {

    /** Nom du produit. */
    private String nameProduct;

    /** Quantité en millilitres. */
    private int quantityML;

    /** Prix unitaire du produit. */
    private double price;

    /** Quantité demandée par l'utilisateur. */
    private int askingQuantity;

    /** Nom de la classe d'origine du produit (ex: Coffee, Chocolate). */
    private String originaClassName;

    /**
     * Constructeur de base.
     *
     * @param nameProduct Nom du produit.
     * @param quantityML  Quantité (en ml).
     * @param price       Prix unitaire.
     */
    public ProductForPanier(String nameProduct, int quantityML, double price) {
        this.nameProduct = nameProduct;
        this.quantityML = quantityML;
        this.price = price;
        this.askingQuantity = 1;
    }

    /**
     * Retourne le nom de la classe d'origine du produit (ex: "Coffee").
     *
     * @return Nom de la classe.
     */
    public String getOriginaClassName() {
        return originaClassName;
    }

    /**
     * Définit le nom de la classe d'origine du produit.
     *
     * @param originaClassName Nom de la classe.
     */
    public void setOriginaClassName(String originaClassName) {
        this.originaClassName = originaClassName;
    }

    /**
     * Retourne le nom du produit.
     *
     * @return Nom du produit.
     */
    public String getNameProduct() {
        return nameProduct;
    }

    /**
     * Retourne la quantité du produit (en millilitres).
     *
     * @return Quantité demandée.
     */
    public int getQuantityML() {
        return quantityML;
    }

    /**
     * Retourne le prix unitaire du produit.
     *
     * @return Prix.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Retourne la quantité demandée par l'utilisateur.
     *
     * @return Quantité demandée.
     */
    public int getAskingQuantity() {
        return askingQuantity;
    }

    /**
     * Définit la quantité demandée pour ce produit.
     *
     * @param askingQuantity Nouvelle quantité demandée.
     */
    public void setAskingQuantity(int askingQuantity) {
        this.askingQuantity = askingQuantity;
    }
}
