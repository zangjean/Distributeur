package application.model.connexion;

import application.model.user.User;
import application.model.user.UserManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Pair;

public class Connexion {

    private User connectedUser;
    private boolean is_connected;
    private BooleanProperty is_connected_property= new SimpleBooleanProperty(false);
    ;
    private UserManager userManager;


    public Connexion() {
        this.connectedUser = null;
        this.is_connected = false;
        this.userManager = new UserManager();
    }

    private void setupConnexion(User user) {
        this.connectedUser = user;
        this.is_connected = true;
        this.is_connected_property.set(true);
    }
    public User getConnectedUser() {
        return connectedUser;
    }
    public boolean is_connected() {
        return is_connected;
    }

    public void disconnect() {
        this.connectedUser = null;
        this.is_connected = false;
        this.is_connected_property.set(false);
    }

    // Méthode pour relier la propriété is_connected à JavaFX
    public BooleanProperty isConnectedProperty() {
        return is_connected_property;
    }


    public boolean userCanConnect(String login, String password) {
        boolean canConnect = false;
        for(User user: userManager.getAll_users()){
            if(user.getUsername().equals(login) && user.getPassword().equals(password)){
                canConnect = true;
                setupConnexion(user);
                break;
            }

        }
        return canConnect;
    }

    public Pair<Boolean,String> createAccount(String login, String password){
        return this.userManager.createAccount(login, password);
    }
}
