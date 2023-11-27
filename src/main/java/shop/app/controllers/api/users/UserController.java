package shop.app.controllers.api.users;

import org.springframework.web.bind.annotation.*;
import shop.app.Services.users.UserService;
import shop.app.config.JwtTokenUtil;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.helper.ui_models.UserVM;
import shop.app.models.users.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;
    public UserController(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ServiceResponse<UserVM> login(@RequestBody User user){
            User userData = userService.auth(user.getUsername(), user.getPassword());
            if (userData == null)
                return new  ServiceResponse<UserVM>("Incorrect username or password",ResponseStatus.FAILED);
            UserVM userVM = new UserVM(userData);
            String token = jwtTokenUtil.generateToken(userVM);
            userVM.setToken(token);
            return new ServiceResponse<UserVM>(userVM,ResponseStatus.SUCCESS);
    }

    @GetMapping("/{id}")
    public ServiceResponse<UserVM> getById(@PathVariable long id) {
        try {
            User result = userService.getById(id);
            return new ServiceResponse<UserVM>(new UserVM(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<UserVM>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<UserVM> add(@RequestBody /*@Valid*/User data) {
        try {
            User result = userService.add(data);
            return new ServiceResponse<UserVM>(new UserVM(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<UserVM>(e);
        }
    }

    @PutMapping("/update")
    public ServiceResponse<UserVM> update(@RequestBody User data) {
        try {
            User result = userService.update(data);
            return new ServiceResponse<UserVM>(new UserVM(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<UserVM>(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = userService.deleteById(id);
            return new ServiceResponse<Boolean>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<Boolean>(e);
        }
    }

    @PutMapping("/changePass")
    public ServiceResponse<UserVM> changePassword(@RequestBody UserVM data) {
        try {
            User result = userService.changePassword(data.getId(),data.getPassword(),data.getNewPassword());
            return new ServiceResponse<UserVM>(new UserVM(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<UserVM>(e);
        }
    }

}
