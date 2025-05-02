package application.model.distrib.productModel.product.beverage.sugar.all;

import application.model.distrib.productModel.product.beverage.sugar.CanAddSugar;

/**
 * Classe représentant un vin chaud.
 * Hérite de {@link CanAddSugar}, permettant de gérer l'ajout de sucre.
 * Le prix par défaut est retourné comme 0, à personnaliser si besoin.
 */
public class HotWine extends CanAddSugar {

    /**
     * Constructeur de la classe {@code HotWine}.
     *
     * @param name Nom du produit (ex. "Vin chaud traditionnel").
     */
    public HotWine(String name) {
        super(name);
    }

    /**
     * Retourne le prix du vin chaud.
     * À surcharger si une logique tarifaire est définie ailleurs.
     *
     * @return Prix actuel (0 par défaut).
     */
    @Override
    public double getPrice() {
        return 0;
    }
}
