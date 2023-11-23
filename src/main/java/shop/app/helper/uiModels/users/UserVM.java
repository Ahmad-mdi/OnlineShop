package shop.app.helper.uiModels.users;

import shop.app.enums.UserRole;
import shop.app.models.users.User;

public class UserVM {
    private long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private UserRole role;
    private boolean enable;
    private String token;


    public UserVM() {
    }
    public UserVM(User user) {
        setId(user.getId());
        setEmail(user.getEmail());
        setEnable(user.isEnable());
        setFirstname(user.getFirstname());
        setLastname(user.getLastname());
        setRole(user.getRole());
        setRole(user.getRole());
        setUsername(user.getUsername());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
