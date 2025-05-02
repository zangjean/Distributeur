package application.model.connexion;

import application.model.user.User;
import application.model.user.UserManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Pair;

/**
 * Classe responsable de la gestion de la connexion d'un utilisateur.
 * Elle gère l'état de connexion, les tentatives d'authentification, et la création de comptes.
 */
public class Connexion {

    /** Utilisateur actuellement connecté. */
    private User connectedUser;

    /** État de connexion : vrai si un utilisateur est connecté. */
    private boolean is_connected;

    /** Propriété JavaFX liée à l'état de connexion, utilisée pour la liaison avec l'interface graphique. */
    private final BooleanProperty is_connected_property = new SimpleBooleanProperty(false);

    /** Gestionnaire des utilisateurs (base de données en mémoire). */
    private UserManager userManager;

    /**
     * Constructeur par défaut.
     * Initialise l'état comme déconnecté et instancie le gestionnaire d'utilisateurs.
     */
    public Connexion() {
        this.connectedUser = null;
        this.is_connected = false;
        this.userManager = new UserManager();
    }

    /**
     * Initialise l'état de connexion avec l'utilisateur fourni.
     *
     * @param user L'utilisateur à connecter.
     */
    private void setupConnexion(User user) {
        this.connectedUser = user;
        this.is_connected = true;
        this.is_connected_property.set(true);
    }

    /**
     * Retourne l'utilisateur actuellement connecté.
     *
     * @return L'objet {@link User} connecté, ou {@code null} si aucun.
     */
    public User getConnectedUser() {
        return connectedUser;
    }

    /**
     * Indique si un utilisateur est actuellement connecté.
     *
     * @return {@code true} si connecté, sinon {@code false}.
     */
    public boolean is_connected() {
        return is_connected;
    }

    /**
     * Déconnecte l'utilisateur actuellement connecté et réinitialise l'état de connexion.
     */
    public void disconnect() {
        this.connectedUser = null;
        this.is_connected = false;
        this.is_connected_property.set(false);
    }

    /**
     * Retourne la propriété JavaFX liée à l'état de connexion.
     * Utile pour lier dynamiquement des composants UI.
     *
     * @return Propriété {@link BooleanProperty} représentant l'état de connexion.
     */
    public BooleanProperty isConnectedProperty() {
        return is_connected_property;
    }

    /**
     * Tente d'authentifier un utilisateur à partir d'un identifiant et d'un mot de passe.
     *
     * @param login    Nom d'utilisateur.
     * @param password Mot de passe.
     * @return {@code true} si l'utilisateur est reconnu et connecté, sinon {@code false}.
     */
    public boolean userCanConnect(String login, String password) {
        for (User user : userManager.getAll_users()) {
            if (user.getUsername().equals(login) && user.getPassword().equals(password)) {
                setupConnexion(user);
                return true;
            }
        }
        return false;
    }

    /**
     * Crée un compte utilisateur avec le login et le mot de passe fournis.
     *
     * @param login    Nom d'utilisateur souhaité.
     * @param password Mot de passe souhaité.
     * @return Un {@link Pair} indiquant le succès ou l’échec de la création, avec un message associé.
     */
    public Pair<Boolean, String> createAccount(String login, String password) {
        return this.userManager.createAccount(login, password);
    }

    /**
     * Retourne le gestionnaire d'utilisateurs.
     *
     * @return L'instance de {@link UserManager}.
     */
    public UserManager getUserManager() {
        return userManager;
    }
}
