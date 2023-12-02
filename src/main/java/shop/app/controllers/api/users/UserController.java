package shop.app.controllers.api.users;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import shop.app.ConstantsMessage;
import shop.app.Services.users.UserService;
import shop.app.config.JwtTokenUtil;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.helper.ui_models.UserVM;
import shop.app.helper.utils.SecurityUtils;
import shop.app.models.users.User;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final JwtTokenUtil jwtTokenUtil;
    private final SecurityUtils securityUtils;
    private final UserService userService;

    @PostMapping("/login")
    public ServiceResponse<UserVM> login(@RequestBody User user) {
        User userData = userService.auth(user.getUsername(), user.getPassword());
        if (userData == null)
            return new ServiceResponse<>(ConstantsMessage.MSG2, ResponseStatus.FAILED);
        UserVM userVM = new UserVM(userData);
        String token = jwtTokenUtil.generateToken(userVM);
        userVM.setToken(token);
        return new ServiceResponse<>(userVM, ResponseStatus.SUCCESS);
    }

    @GetMapping("/{id}")
    public ServiceResponse<UserVM> getById(@PathVariable long id) {
        try {
            User result = userService.getById(id);
            return new ServiceResponse<>(new UserVM(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<>(e);
        }
    }

    @PostMapping("/add")
    public ServiceResponse<UserVM> add(@RequestBody /*@Valid*/User data) {
        try {
            data.setPassword(securityUtils.encryptSHA1(data.getPassword()));
            User result = userService.add(data);
            return new ServiceResponse<>(new UserVM(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<>(e);
        }
    }

    @PutMapping("/update")
    public ServiceResponse<UserVM> update(@RequestBody User data) {
        try {
            User result = userService.update(data);
            return new ServiceResponse<>(new UserVM(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<>(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = userService.deleteById(id);
            return new ServiceResponse<>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<>(e);
        }
    }

    @PutMapping("/changePass")
    public ServiceResponse<UserVM> changePassword(@RequestBody UserVM data) {
        try {
            User result = userService.changePassword(data.getId(), data.getPassword(), data.getNewPassword());
            return new ServiceResponse<>(new UserVM(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ServiceResponse<>(e);
        }
    }

}
