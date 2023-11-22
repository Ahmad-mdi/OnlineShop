package shop.app.controllers.api.users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.app.helper.ui.ResponseStatus;
import shop.app.helper.ui.ServiceResponse;
import shop.app.models.users.User;

@RestController
@RequestMapping("api/user")
public class UserController {
    @GetMapping("test")
    public ServiceResponse<User> index(){
        return new ServiceResponse<User>(new User(), ResponseStatus.SUCCESS);
    }
}
