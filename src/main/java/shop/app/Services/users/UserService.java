package shop.app.Services.users;

import org.springframework.stereotype.Service;
import shop.app.helper.exceptions.DataNotFoundException;
import shop.app.helper.utils.SecurityUtils;
import shop.app.models.users.User;
import shop.app.repositories.users.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final SecurityUtils securityUtils;

    //construct method:
    public UserService(UserRepository userRepository, SecurityUtils securityUtils) {
        this.userRepository = userRepository;
        this.securityUtils = securityUtils;
    }

    public User auth(String username,String password){
        try {
            password = securityUtils.encryptSHA1(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return userRepository.findFirstByUsernameAndPassword(username,password);
    }

    public User getByUsername(String username){
        return userRepository.findFirstByUsername(username);
    }

    public User getById(long id) {
        Optional<User> data = userRepository.findById(id);
        if (data.isPresent()) return data.get();
        return null;

    }
    public User add(User data) throws Exception {
        if (data.getUsername()==null || data.getPassword()==null)
            throw new Exception("username password notNull!");
        return userRepository.save(data);
    }

    public User update(User data) throws DataNotFoundException {
        User oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setEmail(data.getEmail());
        oldData.setFirstname(data.getFirstname());
        oldData.setLastname(data.getLastname());
        oldData.setEnable(data.isEnable());
        return userRepository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        User oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        userRepository.deleteById(id);
        return true;
    }

    //for updatePass:
    public User changePassword(long id,String oldPassword,String newPassword) throws Exception {
        try {
            oldPassword = securityUtils.encryptSHA1(oldPassword);
            newPassword = securityUtils.encryptSHA1(newPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }        User userChanePass = getById(id);
        if (userChanePass == null)
            throw new DataNotFoundException("user not found!");
        if (!userChanePass.getPassword().equals(oldPassword))
            throw new Exception("Invalid old password");
        userChanePass.setPassword(newPassword);
        return userRepository.save(userChanePass);
    }
}
