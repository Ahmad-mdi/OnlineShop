package shop.app.helper.ui_models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.app.enums.UserRole;
import shop.app.models.users.User;

@Data // for geeter and setter
@AllArgsConstructor // auto generate constructor
@NoArgsConstructor // if == not consturctor
public class UserVM {
    private long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
//    @JsonIgnore// not show data in response
    private String newPassword;
    private String email;
    private UserRole role;
    private boolean enable;
    private String token;
    private String fullName;

    public UserVM(User user) {
        setId(user.getId());
        setEmail(user.getEmail());
        setEnable(user.isEnable());
        setFirstname(user.getFirstname());
        setLastname(user.getLastname());
        setRole(user.getRole());
        setRole(user.getRole());
        setUsername(user.getUsername());
        setFullName(getFirstname()+" "+getLastname());
    }

}
