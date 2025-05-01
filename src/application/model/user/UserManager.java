package application.model.user;

import application.utils.UtilsSaveLoad;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private ArrayList<User> all_users;
    private String allSpecialChar = " /!*,?;.:<>#~{'&[(|-_^@)]=}=° ";
    private String allNumber = "0123456789";
    private int minLengthPassword = 6;
    private static final String USERS_FILE = "users.dat";
    private static String USERS_FILE_FULL = "";
    private int compt=0;


    public UserManager() {
        USERS_FILE_FULL= UtilsSaveLoad.createNewFileIfNotExist(USERS_FILE);
        this.all_users = (ArrayList<User>) loadUsers();
        initAdmin_Jean();


    }


    public void saveUsers(List<User> newUsers, String utils) {
        List<User> existingUsers = loadUsers();

        for (User newUser : newUsers) {
            if (!existingUsers.contains(newUser)) {
                existingUsers.add(newUser);
            }else{
                for(User existUser:existingUsers){
                    if(existUser.equals(newUser)){
                        if(utils.equals("utils")){
                            System.out.println("SAVE USER : "+newUser.getUsername()+" Compte existant POINTS_avant: "+existUser.getPoints()+" POINTS_APRES: "+newUser.getPoints());
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
        saveUsers(users, "userManager");
        this.all_users= (ArrayList<User>) loadUsers();
    }


    public ArrayList<User> getAll_users() {
        return all_users;
    }

    public Pair<Boolean, String> createAccount(String username, String password) {
        boolean accountCreated = false;
        String message = "Compte non créé"; // Exemple de message
        if (!userAlreadyExist(username)) {
            User newUser= new User(username,password);

            Pair<Boolean, String> passwordIsValide = passwordIsValide(password);
            if(passwordIsValide.getKey()==true){
                message="Compte crée avec succes";
                accountCreated = true;
                this.all_users.add(newUser);
                saveUsers(this.all_users, "UserManager");

            }else{
                System.out.println("Compte NON créé");
                message=passwordIsValide.getValue();
            }
        }else{
            message="Compte deja existant !";
        }
        return new Pair<>(accountCreated, message);
    }


    private boolean userAlreadyExist(String username) {
        boolean result = false;
        for (User user : this.all_users) {
            if (user.getUsername().equals(username)) {
                result = true;
                break;
            }
        }
        return result;
    }

    // CHECK PASSWORD------------

    private Pair<Boolean, String> passwordIsValide(String password) {
        boolean result = false;
        String message = "Mot de passe invalide";
        //Je veux un mot de passe de au moins 6 char, au moins in chiffre
        // et un caractere special parmis   /!*,?;.:<>#~{'"&[(|-\_^@)]=}=°
        if (password.length() >= this.minLengthPassword) {
            if(containOneSpecialChar(password)){
                if(containOneNumber(password)){
                    result = true;
                    message="Mot de passe valide";
                }else{
                    message="Le mot de passe doit contenir au moins un chiffre !";
                }
            }else {
                message="Le mot de passe doit contenir au moins un caractère parmis: \n"+this.allSpecialChar+" !";
            }
        }else {
            message = "La longeur du mot de passe doit avoir au moins "+this.minLengthPassword+" charactères! ";
        }
        return new Pair<>(result, message);

    }

    private boolean isSpecialChar(String s) {
        if (s.length() != 1) {
            return false; // s n'est pas un seul caractère
        }
        return this.allSpecialChar.contains(s);
    }

    private boolean containOneSpecialChar(String string){
        for (int i = 0; i < string.length(); i++) {
            if(isSpecialChar(String.valueOf(string.charAt(i)))){
                return true;
            }
        }
        return false;
    }

    private boolean isNumber(String string){
        if(string.length() != 1){
            return false;
        }
        return this.allNumber.contains(string);
    }

    private boolean containOneNumber(String string){
        for (int i = 0; i < string.length(); i++) {
            if(isNumber(String.valueOf(string.charAt(i)))){
                return true;
            }
        }
        return false;
    }






}
