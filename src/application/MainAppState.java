package application;

import application.controller.MenuBarController;
import application.controller.connexion.ConnexionController;

public class MainAppState {

    private static ConnexionController connexionController;
    private static MenuBarController menuBarController;

    // Accesseurs pour ConnexionController
    public static ConnexionController getConnexionController() {
        return connexionController;
    }

    public static void setConnexionController(ConnexionController controller) {
        connexionController = controller;
    }

    // Accesseurs pour MenuBarController
    public static MenuBarController getMenuBarController() {
        return menuBarController;
    }

    public static void setMenuBarController(MenuBarController controller) {
        menuBarController = controller;
    }
}