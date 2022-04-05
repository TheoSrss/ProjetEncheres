package fr.eni.encheres.bo;

public class User {
    private int id;
    private String username;
    private String surname;
    private String firstName;
    private String email;
    private String phone;
    private String street;
    private String postalCode;
    private String city;
    private String password;
    private int credit;
    private boolean isAdmin;
    //    private List<Enchere> encheres;
//    private List<Article> articles;

    public User(String username,String firstName){
        this.username = username;
        this.firstName = firstName;
    }
    public User(int id, String username, String surname, String firstName, String email, String phone, String street, String postalCode, String city, String password, int credit, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.password = password;
        this.credit = credit;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }


}
