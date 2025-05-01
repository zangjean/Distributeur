package application.utils;

import application.Main;
import application.model.user.User;

import java.util.ArrayList;

import static application.Main.panier;

public abstract class Utils {

    public static void executeBeforeClosing() {
        // Logique à exécuter avant la fermeture
        System.out.println("Exécution de la logique avant fermeture...");
        //si il n'y apas d'utiisateur, pas besoin de sauvegarderle panier a la fermeture
        // si il y a un utilisateur il faut sauvegarder le contenu du panieraffin de le sauvegarder
        if(panier.getUser()== null){
            System.out.println("Pas d'utilisateur");
        }else {
            System.out.println("Il y a un utilisateur courant -> il faut save le panier");
            if(panier.getProducts().isEmpty()){
                //si le panier est vide je n'ai rien besoin de sauvegarder
                System.out.println("Le panier est vide -> pas besoin de sauvegarder");
            }else{
                System.out.println("Le panier n'est pas vide -> je sauvegarde le panier");

            }
            ArrayList<User> users = new ArrayList<>();
            users.add(panier.getUser());
            Main.connexion.getUserManager().saveUsers(users,"utils");

        }
    }
}
