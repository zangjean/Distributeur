package application.utils;

import application.MainApp;
import application.model.user.User;

import java.util.ArrayList;

import static application.MainApp.panier;

/**
 * Classe utilitaire contenant des méthodes statiques pour diverses opérations générales.
 */
public abstract class Utils {

    /**
     * Méthode appelée avant la fermeture de l'application.
     * Elle vérifie si un utilisateur est connecté et si le panier contient des produits.
     * Si c'est le cas, elle sauvegarde l'utilisateur avec ses points actualisés.
     */
    public static void executeBeforeClosing() {
        System.out.println("Exécution de la logique avant fermeture...");

        if (panier.getUser() == null) {
            System.out.println("Pas d'utilisateur");
        } else {
            System.out.println("Il y a un utilisateur courant -> il faut sauvegarder le panier");

            if (panier.getProducts().isEmpty()) {
                System.out.println("Le panier est vide -> pas besoin de sauvegarder");
            } else {
                System.out.println("Le panier n'est pas vide -> je sauvegarde le panier");
            }

            // Sauvegarde uniquement l'utilisateur courant (avec points mis à jour)
            ArrayList<User> users = new ArrayList<>();
            users.add(panier.getUser());
            MainApp.connexion.getUserManager().saveUsers(users, "utils");
        }
    }
}
