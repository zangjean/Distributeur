package application.model.distrib.productModel.product.beverage.sugar.all;

import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;

/**
 * Classe représentant un café.
 * Hérite de {@link CanAddSugar} pour gérer l'ajout de sucre.
 * Le prix de base est géré ailleurs (probablement via une carte des prix par quantité).
 */
public class Coffee extends CanAddSugar {

    /**
     * Constructeur de la classe {@code Coffee}.
     *
     * @param name Nom du café (ex. "Expresso", "Latte").
     */
    public Coffee(String name) {
        super(name);
    }

    /**
     * Retourne le prix du café.
     * Cette méthode est surchargée mais ici retourne 0 par défaut.
     * Le prix réel peut être déterminé dynamiquement ailleurs selon la taille.
     *
     * @return Prix du café (actuellement 0).
     */
    @Override
    public double getPrice() {
        return 0;
    }
}

