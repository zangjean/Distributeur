package application.model.distrib.saveFav;
import application.model.distrib.panier.Panier;
import application.model.distrib.panier.ProductForPanier;
import application.utils.UtilsSaveLoad;

import java.io.*;
import java.util.ArrayList;

public class FavoriteProductForSaveManager{

    private static final String FAVEPROD_FILE = "faveprod.dat";
    private String FAVPROD_FILE_FULL;


    FavoriteProductForSave favoriteProductForSave;

    public FavoriteProductForSaveManager() {
        this.favoriteProductForSave = new FavoriteProductForSave();
        this.FAVPROD_FILE_FULL = UtilsSaveLoad.createNewFileIfNotExist(FAVEPROD_FILE);
    }


    public void saveFaveProd(){
        try {
            FileOutputStream fos = new FileOutputStream(FAVPROD_FILE_FULL);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.favoriteProductForSave);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // java
    public void loadFaveProd(){
        File file = new File(FAVPROD_FILE_FULL);
        if (!file.exists() || file.length() == 0) {
            // Si le fichier n'existe pas ou est vide, il n'y a rien à charger.
            System.out.println("[INFO] Aucune sauvegarde trouvée (fichier vide ou inexistant).");
            return;
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.favoriteProductForSave = (FavoriteProductForSave) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FavoriteProductForSave getFavoriteProductForSave(){
        return this.favoriteProductForSave;
    }


    public void run(Panier panier) {
        loadFaveProd();
        for (ProductForPanier productForPanier:panier.getProducts()){
            this.favoriteProductForSave.addFavProd(productForPanier.getNameProduct(),productForPanier.getAskingQuantity());
        }
        saveFaveProd();
        System.out.println(this.favoriteProductForSave.toString());
    }

    private ArrayList<String> returnAllFavProdNames() {
        loadFaveProd();
        ArrayList<String> favProdNames = new ArrayList<>();
        // Parcours des clés de la HashMap représentant les noms des produits favoris
        for (String prodName : this.favoriteProductForSave.getFavProds().keySet()) {
            favProdNames.add(prodName);
        }
        return favProdNames;
    }

    public ArrayList<String> return3MostFavProd(){
        System.out.println("return3MostFavProd");
        System.out.println("DEBUT LISTE TOT FAV");
        ArrayList<String> favProdNames = returnAllFavProdNames();
        System.out.println(favProdNames);
        System.out.println("FIN LISTE TOT FAV");



        ArrayList<String> res = new ArrayList<>();

        if(favProdNames.size()>3){
            ArrayList<String> favProdNamesTemps = favProdNames;
            for(int i=0;i<3;i++){
                String temp=returnMostFavProd(favProdNamesTemps);
                res.add(temp);
                favProdNamesTemps.remove(temp);
            }
            return res;

        }else {
            return favProdNames;
        }
    }

    private String returnMostFavProd(ArrayList<String> favProdNames){
        int max =0;
        String mostFavProdName="";
        for(String prodName:favProdNames){
            if(max<this.favoriteProductForSave.getFavProds().get(prodName)){
                max=this.favoriteProductForSave.getFavProds().get(prodName);
                mostFavProdName=prodName;
            }
        }
        return mostFavProdName;

    }



}
