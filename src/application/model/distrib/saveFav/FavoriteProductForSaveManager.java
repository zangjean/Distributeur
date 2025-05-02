package application.model.distrib.saveFav;

import application.model.distrib.panier.Panier;
import application.model.distrib.panier.ProductForPanier;
import application.utils.UtilsSaveLoad;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe responsable de la gestion de la sauvegarde et du chargement
 * des produits favoris (par fréquence d'achat), dans un fichier local.
 */
public class FavoriteProductForSaveManager {

    /** Nom du fichier de sauvegarde. */
    private static final String FAVEPROD_FILE = "faveprod.dat";

    /** Chemin complet vers le fichier. */
    private String FAVPROD_FILE_FULL;

    /** Données des produits favoris (nom + nombre de fois choisis). */
    private FavoriteProductForSave favoriteProductForSave;

    /**
     * Constructeur : initialise les données et s'assure que le fichier existe.
     */
    public FavoriteProductForSaveManager() {
        this.favoriteProductForSave = new FavoriteProductForSave();
        this.FAVPROD_FILE_FULL = UtilsSaveLoad.createNewFileIfNotExist(FAVEPROD_FILE);
    }

    /**
     * Sauvegarde l'état actuel des produits favoris dans un fichier `.dat`.
     */
    public void saveFaveProd() {
        try (FileOutputStream fos = new FileOutputStream(FAVPROD_FILE_FULL);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this.favoriteProductForSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge les produits favoris à partir du fichier.
     * Affiche un message si le fichier est vide ou inexistant.
     */
    public void loadFaveProd() {
        File file = new File(FAVPROD_FILE_FULL);
        if (!file.exists() || file.length() == 0) {
            System.out.println("[INFO] Aucune sauvegarde trouvée (fichier vide ou inexistant).");
            return;
        }
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.favoriteProductForSave = (FavoriteProductForSave) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne l'objet représentant tous les produits favoris.
     *
     * @return Instance de {@link FavoriteProductForSave}.
     */
    public FavoriteProductForSave getFavoriteProductForSave() {
        return this.favoriteProductForSave;
    }

    /**
     * Met à jour les favoris à partir du panier fourni :
     * - Charge l'existant
     * - Ajoute les produits du panier
     * - Sauvegarde le nouveau total
     *
     * @param panier Panier d'achat courant.
     */
    public void run(Panier panier) {
        loadFaveProd();
        for (ProductForPanier product : panier.getProducts()) {
            this.favoriteProductForSave.addFavProd(product.getNameProduct(), product.getAskingQuantity());
        }
        saveFaveProd();
        System.out.println(this.favoriteProductForSave.toString());
    }

    /**
     * Retourne la liste de tous les noms de produits ayant été enregistrés comme favoris.
     *
     * @return Liste de noms.
     */
    private ArrayList<String> returnAllFavProdNames() {
        loadFaveProd();
        return new ArrayList<>(this.favoriteProductForSave.getFavProds().keySet());
    }

    /**
     * Retourne les 3 produits les plus populaires (en nombre d'achats).
     *
     * @return Liste (taille ≤ 3) des noms de produits favoris.
     */
    public ArrayList<String> return3MostFavProd() {
        System.out.println("return3MostFavProd");
        System.out.println("DEBUT LISTE TOT FAV");

        ArrayList<String> favProdNames = returnAllFavProdNames();
        System.out.println(favProdNames);
        System.out.println("FIN LISTE TOT FAV");

        ArrayList<String> res = new ArrayList<>();

        if (favProdNames.size() > 3) {
            ArrayList<String> favProdNamesTemps = new ArrayList<>(favProdNames);
            for (int i = 0; i < 3; i++) {
                String temp = returnMostFavProd(favProdNamesTemps);
                res.add(temp);
                favProdNamesTemps.remove(temp);
            }
            return res;
        } else {
            return favProdNames;
        }
    }

    /**
     * Identifie le produit avec le plus grand nombre d'achats dans la liste fournie.
     *
     * @param favProdNames Liste de noms de produits à comparer.
     * @return Nom du produit le plus populaire.
     */
    private String returnMostFavProd(ArrayList<String> favProdNames) {
        int max = 0;
        String mostFavProdName = "";
        for (String prodName : favProdNames) {
            int count = this.favoriteProductForSave.getFavProds().getOrDefault(prodName, 0);
            if (count > max) {
                max = count;
                mostFavProdName = prodName;
            }
        }
        return mostFavProdName;
    }
}
