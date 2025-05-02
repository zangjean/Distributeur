package application.model.distrib.productModel.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe abstraite représentant un produit générique, comme une boisson ou un aliment.
 * Fournit des propriétés communes telles que le nom, les allergènes, le nutriscore
 * et une carte de prix en fonction de la quantité.
 */
public abstract class Product {

    /** Nom du produit. */
    private String name;

    /** Liste des allergènes alimentaires associés au produit. */
    private ArrayList<String> foodAllergen;

    /**
     * Carte des prix selon la quantité :
     * - Pour les boissons : quantités en millilitres (ex: 240 → 2.5€).
     * - Pour la nourriture : clés symboliques (1=Small, 2=Medium, 3=Large).
     */
    private Map<Integer, Double> quantityPriceMap;

    /** Nutriscore nutritionnel du produit (valeurs de A à E). */
    private Nutriscore nutriscore;

    /**
     * Constructeur du produit.
     *
     * @param name Nom du produit.
     */
    public Product(String name) {
        this.name = name;
        this.foodAllergen = new ArrayList<>();
        this.quantityPriceMap = new HashMap<>();
    }

    /**
     * Retourne le nom du produit.
     *
     * @return Nom du produit.
     */
    public String getName() {
        return name;
    }

    /**
     * Modifie le nom du produit.
     *
     * @param name Nouveau nom.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne la liste des allergènes associés.
     *
     * @return Liste des allergènes.
     */
    public ArrayList<String> getFoodAllergen() {
        return foodAllergen;
    }

    /**
     * Remplace complètement la liste des allergènes.
     *
     * @param foodAllergen Nouvelle liste.
     */
    public void setFoodAllergen(ArrayList<String> foodAllergen) {
        this.foodAllergen = foodAllergen;
    }

    /**
     * Ajoute un allergène à la liste existante.
     *
     * @param foodAllergen Allergène à ajouter.
     */
    public void addOneFoodAllergen(String foodAllergen) {
        this.foodAllergen.add(foodAllergen);
    }

    /**
     * Définit le nutriscore depuis une chaîne (doit être "A" à "E").
     *
     * @param nutriscore Lettre correspondant au nutriscore.
     */
    public void setNutriscore(String nutriscore) {
        try {
            this.nutriscore = Nutriscore.valueOf(nutriscore);
        } catch (IllegalArgumentException e) {
            System.out.println("Valeur de nutriscore invalide: " + nutriscore);
        }
    }

    /**
     * Retourne le nutriscore actuel.
     *
     * @return Nutriscore du produit.
     */
    public Nutriscore getNutriscore() {
        return nutriscore;
    }

    /**
     * Retourne la carte des prix selon la quantité.
     *
     * @return Map quantité → prix.
     */
    public Map<Integer, Double> getQuantityPriceMap() {
        return quantityPriceMap;
    }

    /**
     * Définit complètement la carte quantité/prix.
     *
     * @param quantityPriceMap Nouvelle carte à affecter.
     */
    public void setQuantityPriceMap(Map<Integer, Double> quantityPriceMap) {
        this.quantityPriceMap = quantityPriceMap;
    }

    /**
     * Ajoute un couple quantité/prix dans la carte.
     *
     * @param quantity Quantité (ml ou taille symbolique).
     * @param price    Prix associé.
     */
    public void addOneQuantityPrice(Integer quantity, Double price) {
        this.quantityPriceMap.put(quantity, price);
    }

    /**
     * Retourne le prix pour une quantité donnée.
     *
     * @param quantity Quantité voulue.
     * @return Prix correspondant, ou {@code null} si absent.
     */
    public Double getOnePrice(Integer quantity) {
        return this.quantityPriceMap.get(quantity);
    }

    /**
     * Modifie le prix d'une quantité existante.
     *
     * @param quantity  Quantité à modifier.
     * @param newPrice  Nouveau prix.
     */
    public void changeOnePrice(Integer quantity, Double newPrice) {
        this.quantityPriceMap.remove(quantity);
        this.quantityPriceMap.put(quantity, newPrice);
    }

    /**
     * Fournit un alias de {@link #getQuantityPriceMap()}.
     *
     * @return Carte des prix.
     */
    public Map<Integer, Double> getPriceMap() {
        return this.quantityPriceMap;
    }

    /**
     * Affiche une représentation textuelle simple du produit.
     *
     * @return Détails du produit.
     */
    @Override
    public String toString() {
        return "\n--------Product-------\n" +
                "Name: " + this.name + "\n" +
                "Nutriscore : " + this.nutriscore + "\n" +
                "Food Allergen : " + this.foodAllergen + "\n";
    }
}
