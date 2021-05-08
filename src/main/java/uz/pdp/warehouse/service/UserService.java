package uz.pdp.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        boolean existsById = userRepository.existsById(id);
        if (existsById) {
            return userRepository.findById(id).get();
        }
        return new User();
    }

    public Result add(User user) {
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(user.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new Result("user with this phone number is exist", false);
        }
        userRepository.save(user);
        return new Result("user saved", true);
    }

    public Result delete(Long id) {
        boolean existsById = userRepository.existsById(id);
        if (existsById) {
            userRepository.deleteById(id);
            return new Result("user deleted", true);
        }
        return new Result("not user exist with this id", false);
    }

    public Result update(User user) {
        Optional<User> byId = userRepository.findById(user.getId());
        if (byId.isPresent()) {
            User user1 = byId.get();
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            user1.setPhoneNumber(user.getPhoneNumber());
            user1.setCode(user.getCode());
            user1.setPassword(user.getPassword());
            user1.setActive(user.isActive());
            user1.setWareHouse(user.getWareHouse());
            userRepository.save(user1);
            return new Result("updated", true);
        }
        return new Result("not user exist with this id", false);
    }
}