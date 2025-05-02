package application.model.user;

import application.utils.UtilsSaveLoad;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Gère les opérations liées aux utilisateurs :
 * - Création de compte avec validation
 * - Sauvegarde/chargement des utilisateurs via un fichier
 * - Vérification de mot de passe
 */
public class UserManager {

    /** Liste de tous les utilisateurs enregistrés. */
    private ArrayList<User> all_users;

    /** Ensemble des caractères spéciaux autorisés pour un mot de passe. */
    private final String allSpecialChar = " /!*,?;.:<>#~{'&[(|-_^@)]=}=° ";

    /** Ensemble des chiffres pour validation de mot de passe. */
    private final String allNumber = "0123456789";

    /** Longueur minimale d'un mot de passe valide. */
    private final int minLengthPassword = 6;

    /** Nom du fichier de sauvegarde. */
    private static final String USERS_FILE = "users.dat";

    /** Chemin complet du fichier de sauvegarde. */
    private static String USERS_FILE_FULL = "";

    /** Compteur interne pour éventuelles statistiques (non utilisé). */
    private int compt = 0;

    /**
     * Constructeur : initialise les utilisateurs et crée le fichier si nécessaire.
     */
    public UserManager() {
        USERS_FILE_FULL = UtilsSaveLoad.createNewFileIfNotExist(USERS_FILE);
        this.all_users = (ArrayList<User>) loadUsers();
        initAdmin_Jean(); // Ajout d'utilisateurs par défaut
    }

    /**
     * Sauvegarde les utilisateurs fournis dans le fichier, sans duplication.
     *
     * @param newUsers Nouveaux utilisateurs à sauvegarder.
     * @param utils    Utilisé pour gérer les messages ou types de mises à jour.
     */
    public void saveUsers(List<User> newUsers, String utils) {
        List<User> existingUsers = loadUsers();
        for (User newUser : newUsers) {
            if (!existingUsers.contains(newUser)) {
                existingUsers.add(newUser);
            } else {
                for (User existUser : existingUsers) {
                    if (existUser.equals(newUser)) {
                        if (utils.equals("utils")) {
                            System.out.println("SAVE USER : " + newUser.getUsername() +
                                    " Compte existant POINTS_avant: " + existUser.getPoints() +
                                    " POINTS_APRES: " + newUser.getPoints());
                            existUser.setPoints(newUser.getPoints());
                        }
                    }
                }
            }
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE_FULL))) {
            oos.writeObject(existingUsers);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
        }
    }

    /**
     * Charge tous les utilisateurs depuis le fichier.
     *
     * @return Liste des utilisateurs chargés.
     */
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(USERS_FILE_FULL);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                users = (List<User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
            }
        }
        return users;
    }

    /**
     * Initialise deux utilisateurs par défaut : un admin et un utilisateur nommé Jean.
     */
    public void initAdmin_Jean() {
        List<User> users = new ArrayList<>();
        User admin = new User("admin", "admin");
        admin.setFirstname("admin");
        admin.setLastname("admin");
        users.add(admin);

        User jean = new User("zangjean", "zangjeanmotdepass");
        jean.setFirstname("Jean");
        jean.setLastname("ZANG");
        users.add(jean);

        saveUsers(users, "userManager");
        this.all_users = (ArrayList<User>) loadUsers();
    }

    /**
     * Retourne tous les utilisateurs connus.
     *
     * @return Liste des utilisateurs.
     */
    public ArrayList<User> getAll_users() {
        return all_users;
    }

    /**
     * Tente de créer un nouveau compte utilisateur.
     *
     * @param username Nom d'utilisateur souhaité.
     * @param password Mot de passe proposé.
     * @return Paire (succès, message).
     */
    public Pair<Boolean, String> createAccount(String username, String password) {
        boolean accountCreated = false;
        String message = "Compte non créé";
        if (!userAlreadyExist(username)) {
            User newUser = new User(username, password);
            Pair<Boolean, String> passwordIsValide = passwordIsValide(password);
            if (passwordIsValide.getKey()) {
                message = "Compte créé avec succès";
                accountCreated = true;
                this.all_users.add(newUser);
                saveUsers(this.all_users, "UserManager");
            } else {
                message = passwordIsValide.getValue();
            }
        } else {
            message = "Compte déjà existant !";
        }
        return new Pair<>(accountCreated, message);
    }

    /**
     * Vérifie si un utilisateur existe déjà par son nom.
     *
     * @param username Nom d'utilisateur.
     * @return {@code true} s'il existe.
     */
    private boolean userAlreadyExist(String username) {
        for (User user : this.all_users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // ================== VALIDATION DU MOT DE PASSE ==================

    /**
     * Vérifie si un mot de passe respecte les règles de sécurité.
     *
     * @param password Mot de passe à valider.
     * @return Paire (valide, message explicatif).
     */
    private Pair<Boolean, String> passwordIsValide(String password) {
        boolean result = false;
        String message = "Mot de passe invalide";

        if (password.length() >= this.minLengthPassword) {
            if (containOneSpecialChar(password)) {
                if (containOneNumber(password)) {
                    result = true;
                    message = "Mot de passe valide";
                } else {
                    message = "Le mot de passe doit contenir au moins un chiffre !";
                }
            } else {
                message = "Le mot de passe doit contenir au moins un caractère spécial parmi :\n" + this.allSpecialChar;
            }
        } else {
            message = "La longueur du mot de passe doit être d'au moins " + this.minLengthPassword + " caractères !";
        }

        return new Pair<>(result, message);
    }

    private boolean isSpecialChar(String s) {
        return s.length() == 1 && this.allSpecialChar.contains(s);
    }

    private boolean containOneSpecialChar(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (isSpecialChar(String.valueOf(string.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumber(String string) {
        return string.length() == 1 && this.allNumber.contains(string);
    }

    private boolean containOneNumber(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (isNumber(String.valueOf(string.charAt(i)))) {
                return true;
            }
        }
        return false;
    }
}
