package pucmm.temas.especiales.e_commerce_app.entities;

import java.util.Date;

public class User {
    private String uuid;
    private String token;
    private String name;
    private String user;
    private String password;
    private String email;
    private String rol;
    private String contact;
    private String dateBirth;
    private String photo;

    public User() { }

    public User(String name, String user, String password, String email, String rol, String contact, String dateBirth) {
        this.name = name;
        this.user = user;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.contact = contact;
        this.dateBirth = dateBirth;
        this.photo = photo;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
