package application.model.distrib.saveFav;

import java.io.Serializable;
import java.util.HashMap;

public class FavoriteProductForSave implements Serializable {

    private HashMap<String,Integer> favProds;
    //<nom du produit,nbr de fois ou il est pris>

    public FavoriteProductForSave() {
        this.favProds=new HashMap<>();
    }

    public HashMap<String,Integer> getFavProds() {
        return favProds;
    }

    public void setFavProds(HashMap<String,Integer> favProds) {
        this.favProds = favProds;
    }

    public void addFavProd(String prodName,int nbFav){
        if(this.favProds.containsKey(prodName)){
            this.favProds.put(prodName,this.favProds.get(prodName)+nbFav);
        }else {
            this.favProds.put(prodName,nbFav);
        }
    }

    @Override
    public String toString(){
        String string = "";
        for (String key : this.favProds.keySet()) {
            string += key + " : " + this.favProds.get(key) + "\n";
        }
        return string;
    }
}
