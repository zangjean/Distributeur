package application.model.distrib.productModel.product.beverage.sugar.all.chocolate;

import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;

import java.util.HashSet;

/**
 * Représente un chocolat chaud personnalisable avec option de lait, arôme unique et plusieurs toppings.
 * Hérite des fonctionnalités liées au sucre via {@link CanAddSugar}.
 */
public class HotChocolate extends CanAddSugar {

    /** Indique si la base est au lait (true) ou à l'eau (false). */
    private Boolean withMilk;

    /** Arôme unique sélectionné pour ce chocolat chaud. */
    private Aroma aroma;

    /** Ensemble des toppings ajoutés au chocolat chaud. */
    private HashSet<Topping> toppings;

    /**
     * Constructeur du chocolat chaud avec nom.
     *
     * @param name Nom du produit.
     */
    public HotChocolate(String name) {
        super(name);
        this.toppings = new HashSet<>();
    }

    /**
     * Calcule le prix total du chocolat chaud basé sur les toppings et l’arôme sélectionnés.
     *
     * @return Prix final du produit.
     */
    @Override
    public double getPrice() {
        return allTopingsPice() + aromaPrice();
    }

    /**
     * Retourne l’arôme sélectionné.
     *
     * @return Arôme du chocolat.
     */
    public Aroma getAroma() {
        return aroma;
    }

    /**
     * Définit l’arôme du chocolat.
     *
     * @param aroma Arôme à appliquer.
     */
    public void setAroma(Aroma aroma) {
        this.aroma = aroma;
    }

    /**
     * Indique si le chocolat est préparé avec du lait.
     *
     * @return {@code true} si lait, sinon {@code false}.
     */
    public Boolean getWithMilk() {
        return withMilk;
    }

    /**
     * Définit si le chocolat est préparé avec du lait.
     *
     * @param withMilk {@code true} pour lait, {@code false} pour eau.
     */
    public void setWithMilk(Boolean withMilk) {
        this.withMilk = withMilk;
    }

    /**
     * Retourne l’ensemble des toppings sélectionnés.
     *
     * @return Ensemble des toppings.
     */
    public HashSet<Topping> getTopings() {
        return toppings;
    }

    /**
     * Définit les toppings appliqués au chocolat.
     *
     * @param toppings Ensemble de toppings.
     */
    public void setTopings(HashSet<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Ajoute un topping au chocolat.
     *
     * @param topping Topping à ajouter.
     */
    public void addToping(Topping topping) {
        this.toppings.add(topping);
    }

    /**
     * Supprime un topping du chocolat.
     *
     * @param topping Topping à retirer.
     */
    public void deleteToping(Topping topping) {
        this.toppings.remove(topping);
    }

    /**
     * Retourne la quantité en millilitres selon la taille demandée.
     *
     * @param size Taille du produit (SMALL, MEDIUM, LARGE).
     * @return Quantité en millilitres.
     */
    public int getSize(String size) {
        switch (size) {
            case "SMALL": return 240;
            case "MEDIUM": return 350;
            case "LARGE": return 450;
            default: return 0;
        }
    }

    /**
     * Calcule le coût total des toppings (0.20€ chacun).
     *
     * @return Prix additionnel lié aux toppings.
     */
    private double allTopingsPice() {
        return 0.20 * this.toppings.size();
    }

    /**
     * Calcule le coût de l’arôme (0.30€ si présent).
     *
     * @return Prix additionnel lié à l’arôme.
     */
    private double aromaPrice(){
        if(this.aroma==null){
            return 0;
        }else {
            return 0.30;
        }
    }
}
