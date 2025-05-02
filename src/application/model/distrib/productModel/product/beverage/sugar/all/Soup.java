package application.model.distrib.productModel.product.beverage.sugar.all;

import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;

/**
 * Classe représentant une soupe chaude.
 * Hérite de {@link CanAddSugar}, permettant de personnaliser avec un niveau de sucre (même si peu commun pour une soupe).
 * Peut être adaptée pour contenir d'autres options comme saveurs, taille, ou condiments.
 */
public class Soup extends CanAddSugar {

    /**
     * Constructeur de la classe {@code Soup}.
     *
     * @param name Nom de la soupe (ex. "Soupe de légumes").
     */
    public Soup(String name) {
        super(name);
    }

    /**
     * Retourne le prix de la soupe.
     * À surcharger si une logique tarifaire est définie ailleurs.
     *
     * @return Prix de la soupe.
     */
    @Override
    public double getPrice() {
        return 0;
    }
}
