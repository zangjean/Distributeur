package application.model.connexion;

import application.model.user.User;
import application.model.user.UserManager;

public class Connexion {

    private User connectedUser;
    private boolean is_connected;
    private UserManager userManager;


    public Connexion() {
        this.connectedUser = null;
        this.is_connected = false;
        this.userManager = new UserManager();
    }

    private void setupConnexion(User user) {
        this.connectedUser = user;
        this.is_connected = true;
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
}
