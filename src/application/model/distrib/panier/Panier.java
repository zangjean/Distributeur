package application.model.distrib.panier;

import application.model.distrib.productModel.product.Product;
import application.model.user.User;

import java.util.ArrayList;

public class Panier {
    private ArrayList<ProductForPanier> products;
    private User user;


    public Panier() {
        products = new ArrayList<>();
        user = null;
    }

    public ArrayList<ProductForPanier> getProducts() {
        return products;
    }

    public void addProduct(Product product, int quantity, double price){
        Boolean inProducts = false;

        for (ProductForPanier productForPanier : products) {
            if(product.getName().equals(productForPanier.getNameProduct())){
                if(productForPanier.getQuantityML() == quantity)
                {
                    productForPanier.setAskingQuantity(productForPanier.getAskingQuantity()+1);
                    inProducts = true;
                    break;
                }
            }
        }
        if(!inProducts){
            products.add(new ProductForPanier(product.getName(),quantity,price));
        }

    }

    public void removeProduct(Product product){
        //products.remove(product);
    }

    public void clear(){
        products.clear();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
