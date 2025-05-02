package application.model.distrib.saveFav;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe représentant un ensemble de produits favoris persistables,
 * avec un compteur du nombre de fois qu'un produit a été acheté.
 * Implémente {@link Serializable} pour permettre la sauvegarde sur disque.
 */
public class FavoriteProductForSave implements Serializable {

    /** Map des produits favoris : nom du produit → nombre de fois qu'il a été acheté. */
    private HashMap<String, Integer> favProds;

    /**
     * Constructeur par défaut.
     * Initialise la map vide.
     */
    public FavoriteProductForSave() {
        this.favProds = new HashMap<>();
    }

    /**
     * Retourne la map complète des produits favoris.
     *
     * @return Map des produits favoris.
     */
    public HashMap<String, Integer> getFavProds() {
        return favProds;
    }

    /**
     * Remplace la map actuelle par une nouvelle.
     *
     * @param favProds Nouvelle map à affecter.
     */
    public void setFavProds(HashMap<String, Integer> favProds) {
        this.favProds = favProds;
    }

    /**
     * Ajoute un produit en favori ou incrémente son compteur si déjà présent.
     *
     * @param prodName Nom du produit.
     * @param nbFav    Nombre de fois à ajouter à son compteur.
     */
    public void addFavProd(String prodName, int nbFav) {
        this.favProds.merge(prodName, nbFav, Integer::sum);
    }

    /**
     * Fournit une représentation textuelle des produits favoris.
     *
     * @return Une chaîne listant chaque produit et son compteur d'achat.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (String key : this.favProds.keySet()) {
            string.append(key).append(" : ").append(this.favProds.get(key)).append("\n");
        }
        return string.toString();
    }
}
