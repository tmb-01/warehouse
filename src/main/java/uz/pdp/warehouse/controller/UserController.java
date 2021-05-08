package uz.pdp.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.warehouse.entity.Client;
import uz.pdp.warehouse.entity.User;
import uz.pdp.warehouse.payload.Result;
import uz.pdp.warehouse.service.ClientService;
import uz.pdp.warehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public Result add(@RequestBody User user) {
        return userService.add(user);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Long id) {
        return userService.delete(id);
    }

    @PutMapping("{id}")
    public Result update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.update(user);
    }
}
