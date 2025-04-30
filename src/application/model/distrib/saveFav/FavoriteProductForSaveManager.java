package application.model.distrib.saveFav;
import application.model.distrib.panier.Panier;
import application.model.distrib.panier.ProductForPanier;

import java.io.*;
import java.util.ArrayList;

public class FavoriteProductForSaveManager{

    FavoriteProductForSave favoriteProductForSave;

    public FavoriteProductForSaveManager() {
        this.favoriteProductForSave = new FavoriteProductForSave();
        createFaveProdFileIfNeeded();
    }

    private static final String FAVEPROD_FILE = "faveprod.dat";

    public void saveFaveProd(){
        try {
            FileOutputStream fos = new FileOutputStream(FAVEPROD_FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.favoriteProductForSave);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFaveProd(){
        try {
            FileInputStream fis = new FileInputStream(FAVEPROD_FILE);
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

    private void createFaveProdFileIfNeeded() {
        File file = new File("faveprod.dat");
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("[INFO] Fichier faveprod.dat créé automatiquement.");
                }
            } catch (IOException e) {
                System.err.println("[ERROR] Impossible de créer faveprod.dat");
                e.printStackTrace();
            }
        }
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
        ArrayList<String> favProdNames = returnAllFavProdNames();
        ArrayList<String> res = new ArrayList<>();

        if(favProdNames.size()<3){
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
