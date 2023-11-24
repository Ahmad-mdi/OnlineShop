package shop.app.controllers.api.users;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.users.UserService;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.helper.ui_models.UserVM;
import shop.app.models.users.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ServiceResponse<UserVM> login(@RequestBody User user){
            User userData = userService.auth(user.getUsername(), user.getPassword());
            if (userData == null)
                return new  ServiceResponse<UserVM>("Incorrect username or password",ResponseStatus.FAILED);
            UserVM userVM = new UserVM(userData);
            userVM.setToken("---------");
            return new ServiceResponse<UserVM>(userVM,ResponseStatus.SUCCESS);
    }
}
