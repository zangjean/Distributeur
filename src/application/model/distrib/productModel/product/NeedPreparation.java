package main.java.editormvc.model.productModel.product;

public interface NeedPreparation {

    //formule pour les boissons: quantitéEnMl / 100 * 15
    //formule pour la nourriture: dépend pas de la taille mais du produit uniquement

    int getPreparationTime(Integer quantity);
}
