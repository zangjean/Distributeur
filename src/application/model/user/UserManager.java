package application.model.user;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private ArrayList<User> all_users;

    public UserManager() {
        this.all_users = new ArrayList<>();

        initAdmin_Jean();

    }
    private static final String USERS_FILE = "users.dat";

    public static void saveUsers(List<User> newUsers) {
        List<User> existingUsers = loadUsers(); // Charger les utilisateurs existants

        // Ajouter uniquement les nouveaux utilisateurs qui n'existent pas déjà
        for (User newUser : newUsers) {
            if (!existingUsers.contains(newUser)) {
                existingUsers.add(newUser);
            }
        }

        // Sauvegarder la liste mise à jour
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(existingUsers);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des utilisateurs : " + e.getMessage());
        }
    }

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(USERS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                users = (List<User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Erreur lors du chargement des utilisateurs : " + e.getMessage());
            }
        }
        return users;
    }

    public void initAdmin_Jean() {
        List<User> users = new ArrayList<>();
        User admin = new User("admin","admin");
        admin.setFirstname("admin");
        admin.setLastname("admin");
        users.add(admin);

        User jean = new User("zangjean","zangjeanmotdepass");
        admin.setFirstname("Jean");
        admin.setLastname("ZANG");
        users.add(jean);
        saveUsers(users);
        this.all_users= (ArrayList<User>) loadUsers();
    }


    public ArrayList<User> getAll_users() {
        return all_users;
    }
}
