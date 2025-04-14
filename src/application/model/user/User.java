package application.model.user;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int[] phone_number= new int[10];
    private String mail_address;

    private int points;
    private int money;

    public User( String username,String password)
    {
        this.username = username;
        this.password = String.valueOf(hashCode());
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // ***************USERNAME*****************
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    // ***************PASSWORD*****************
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ***************FIRSTNAME*****************
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    // ***************LASTNAME*****************
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // ***************PHONE_NUMBER*****************
    public int[] getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int[] phone_number) {
        this.phone_number = phone_number;
    }

    // ***************MAIL*****************
    public String getMail_address() {
        return mail_address;
    }

    public void setMail_address(String mail_address) {
        this.mail_address = mail_address;
    }


    // ***************POINTS*****************
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int add)
    {
        this.money = this.money+add;
    }

    public void removePoints(int remove)
    {
        if(this.money>0)
        {
            this.money = this.money-remove;
        }
    }




    // ***************MONEY*****************
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int add)
    {
        this.money = this.money+add;
    }

    public void removeMoney(int remove)
    {
        if(this.money>0)
        {
            this.money = this.money-remove;
        }
    }
}
