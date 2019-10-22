package pucmm.temas.especiales.e_commerce_app.entities;

public class User {
    private long id;
    private String name;
    private String user;
    private String email;
    private String password;
    private String contact;
    private String photo;
    private boolean isProvider;
    private long token;

    public User() { }

    public User(String name, String user, String email, String password, String contact, String photo, boolean isProvider) {
        this.name = name;
        this.user = user;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.photo = photo;
        this.isProvider = isProvider;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isProvider() {
        return isProvider;
    }

    public void setProvider(boolean provider) {
        isProvider = provider;
    }

    public long getToken() {
        return token;
    }

    public void setToken(long token) {
        this.token = token;
    }
}
