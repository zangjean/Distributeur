package application.model.distrib.productModel.product.beverage;

import application.model.distrib.productModel.product.Product;

import java.util.Map;

/**
 * Classe abstraite représentant une boisson.
 * Étend {@link Product} et propose une méthode {@code toString()} enrichie
 * pour afficher les prix par quantité.
 */
public abstract class Beverage extends Product {

    /**
     * Constructeur de la classe {@code Beverage}.
     *
     * @param name Nom de la boisson.
     */
    public Beverage(String name) {
        super(name);
    }

    /**
     * Retourne une représentation textuelle détaillée de la boisson,
     * incluant son nom, nutriscore, allergènes, et prix par taille (ml).
     *
     * @return Chaîne formatée décrivant la boisson.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        if (!this.getQuantityPriceMap().isEmpty()) {
            for (Map.Entry<Integer, Double> entry : this.getQuantityPriceMap().entrySet()) {
                string.append("Quantity (ml): ")
                        .append(entry.getKey())
                        .append(", Price €: ")
                        .append(entry.getValue())
                        .append("\n");
            }
        }

        return "\n--------" + this.getClass().getSimpleName() + "-------\n" +
                "Name: " + this.getName() + "\n" +
                "Nutriscore : " + this.getNutriscore() + "\n" +
                "Food Allergen : " + this.getFoodAllergen() + "\n" +
                string;
    }
}
