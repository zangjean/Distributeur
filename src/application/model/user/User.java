package application.model.user;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber; // Remplacé par String
    private String mail_address;
    private double points;
    private int money;

    public User(String username, String password) {
        this.username = username;
        this.password = password; // Hachage si nécessaire
        this.points = 0;
        this.money = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username); // Comparaison uniquement sur le username
    }

    @Override
    public int hashCode() {
        return Objects.hash(username); // Hash basé sur le username
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    // ------------GUETTER & SETTER
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getMail_address() {
        return mail_address;
    }
    public void setMail_address(String mail_address) {
        this.mail_address = mail_address;
    }
    public double getPoints() {
        return points;
    }
    public void setPoints(double points) {
        this.points = points;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }

//_________________
    public void addPoints(int add) {
        this.points += add;
    }

    public void removePoints(int remove) {
        if (this.points > 0) {
            if(this.points-remove<0){
                this.points=0.0;
            }else {
                this.points -= remove;
            }
        }else{
            System.out.println("ERROR: removePoints -> points negatif");
        }
    }

    public void addMoney(int add) {
        this.money += add;
    }

    public void removeMoney(int remove) {
        if (this.money > 0) {
            if(this.money-remove<0){
                this.money=0;
            }else {
                this.money -= remove;
            }
        }else{
            System.out.println("ERROR: removeMoney -> money negatif");
        }
    }
}
