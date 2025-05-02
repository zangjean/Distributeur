package application.model.distrib.productModel.product.beverage.sugar.all;

import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;

/**
 * Classe représentant un thé chaud.
 * Hérite de {@link CanAddSugar}, permettant la personnalisation avec un niveau de sucre.
 */
public class Tea extends CanAddSugar {

    /**
     * Constructeur de la classe {@code Tea}.
     *
     * @param name Nom du thé (ex. "Thé vert", "Earl Grey").
     */
    public Tea(String name) {
        super(name);
    }

    /**
     * Retourne le prix du thé.
     * À surcharger si une logique tarifaire est définie ailleurs.
     *
     * @return Prix du thé (0 par défaut).
     */
    @Override
    public double getPrice() {
        return 0;
    }
}
