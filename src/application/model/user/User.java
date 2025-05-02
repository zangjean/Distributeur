package application.model.user;

import java.io.Serializable;
import java.util.Objects;

/**
 * Représente un utilisateur de l'application.
 * Contient les informations personnelles, de connexion,
 * ainsi qu’un système de points de fidélité et un solde monétaire.
 */
public class User implements Serializable {

    /** Nom d'utilisateur (identifiant unique). */
    private String username;

    /** Mot de passe associé à l'utilisateur. */
    private String password;

    /** Prénom de l'utilisateur. */
    private String firstname;

    /** Nom de famille de l'utilisateur. */
    private String lastname;

    /** Numéro de téléphone de l'utilisateur. */
    private String phoneNumber;

    /** Adresse e-mail de l'utilisateur. */
    private String mail_address;

    /** Points de fidélité accumulés. */
    private double points;

    /** Montant d'argent disponible. */
    private int money;

    /**
     * Constructeur principal avec identifiants de base.
     *
     * @param username Nom d'utilisateur.
     * @param password Mot de passe.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.points = 0;
        this.money = 0;
    }

    // =================== MÉTHODES UTILES ===================

    /**
     * Compare deux utilisateurs par leur nom d'utilisateur uniquement.
     *
     * @param o Objet à comparer.
     * @return {@code true} si les deux objets représentent le même utilisateur.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    /**
     * Génère un hashCode basé uniquement sur le nom d'utilisateur.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Représentation textuelle basique de l'utilisateur.
     *
     * @return Chaîne avec nom, prénom, et identifiant.
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    // =================== GETTERS & SETTERS ===================

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getMail_address() { return mail_address; }
    public void setMail_address(String mail_address) { this.mail_address = mail_address; }

    public double getPoints() { return points; }
    public void setPoints(double points) { this.points = points; }

    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money; }

    // =================== MÉTHODES DE GESTION ===================

    /**
     * Ajoute des points au compteur de fidélité.
     *
     * @param add Nombre de points à ajouter.
     */
    public void addPoints(int add) {
        this.points += add;
    }

    /**
     * Retire des points de fidélité, sans passer en négatif.
     *
     * @param remove Nombre de points à retirer.
     */
    public void removePoints(int remove) {
        if (this.points > 0) {
            this.points = Math.max(0.0, this.points - remove);
        } else {
            System.out.println("ERROR: removePoints -> points négatifs");
        }
    }

    /**
     * Ajoute de l'argent au solde.
     *
     * @param add Montant à ajouter.
     */
    public void addMoney(int add) {
        this.money += add;
    }

    /**
     * Retire de l'argent du solde, sans passer en négatif.
     *
     * @param remove Montant à retirer.
     */
    public void removeMoney(int remove) {
        if (this.money > 0) {
            this.money = Math.max(0, this.money - remove);
        } else {
            System.out.println("ERROR: removeMoney -> argent négatif");
        }
    }
}
