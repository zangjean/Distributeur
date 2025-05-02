package application.model.distrib.productModel.product.beverage.sugar;

import application.model.distrib.productModel.product.beverage.Beverage;

/**
 * Classe abstraite représentant une boisson à laquelle on peut ajouter du sucre.
 * Étend {@link Beverage} et ajoute la gestion du dosage de sucre.
 */
public abstract class CanAddSugar extends Beverage {

    /** Dose actuelle de sucre (entre 0 et 5). */
    private int actualDose;

    /**
     * Constructeur de base pour les boissons sucrables.
     *
     * @param name Nom de la boisson.
     */
    public CanAddSugar(String name) {
        super(name);
        this.actualDose = 0;
    }

    /**
     * Retourne la dose actuelle de sucre.
     *
     * @return Valeur comprise entre 0 et 5.
     */
    public int getActualDose() {
        return actualDose;
    }

    /**
     * Définit directement la dose actuelle de sucre (privée, contrôlée).
     * Doit rester entre 0 et 5. Si valeur invalide, affiche une erreur.
     *
     * @param actualDose Dose de sucre à définir.
     */
    private void setActualDose(int actualDose) {
        if (0 <= actualDose && actualDose < 6) {
            this.actualDose = actualDose;
        } else {
            System.out.println("ERROR: setActualDose -> sugar between 0 - 5");
        }
    }

    /**
     * Ajoute une quantité de sucre à la dose actuelle, avec un maximum de 5.
     * Si la dose dépasse 5, elle est limitée à 5.
     *
     * @param add Quantité de sucre à ajouter (peut être négative pour réduire).
     */
    public void addSugar(int add) {
        int dose = this.actualDose + add;
        if (dose > 5) {
            setActualDose(5);
        } else if (dose < 0) {
            setActualDose(0);
        } else {
            setActualDose(dose);
        }
    }

    /**
     * Méthode abstraite pour calculer le prix final de la boisson.
     * Doit être implémentée dans les classes concrètes.
     *
     * @return Prix de la boisson.
     */
    public abstract double getPrice();
}
