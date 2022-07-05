package com.example.demo.controller;
import com.example.model.User;
import com.example.services.RoleServiceImpl;
import com.example.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;



@Controller
public class AdminController {
    private UserServiceImpl userService;
    private RoleServiceImpl roleDao;


    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @GetMapping("/admin/user")
    public String helloUser(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        model.addAttribute("id", user.getId());
        model.addAttribute("age", user.getAge());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("roles", user.getRolesString());
        model.addAttribute("password", user.getPassword());
        return "userForAdmin";

    }

    @GetMapping("/admin/users")
    public String showAll(Model model, Principal principal) {
        model.addAttribute("users", userService.listUsers());
        User user = userService.findByUsername(principal.getName()).get();
        String username = user.getUsername();
        String password = user.getPassword();
        int id = user.getId();
        model.addAttribute("id", id);
        String roles = user.getRolesString();
        model.addAttribute("username", username);
        model.addAttribute("roles", roles);
        model.addAttribute("password", password);
        return "users";

    }

    @PatchMapping("/admin/users/{id}")
    public String update(@RequestParam(value = "roles") String[] roles,
                         @RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "age") int age,
                         @PathVariable("id") int id) {
        User user = userService.getUserById(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        int[] roleInt = new int[roles.length];

        for (int i = 0; i < roles.length; i++) {
            roleInt[i] = Integer.parseInt(roles[i]);
        }
        user.setRoles(roleDao.getRolesByIds(roleInt));
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user, Model model, Principal principal) {
        User user2 = userService.findByUsername(principal.getName()).get();
        model.addAttribute("username", user2.getUsername());
        model.addAttribute("roles", user2.getRolesString());
        return "new";
    }


    @PostMapping("/admin/new")
    public String create(@RequestParam(value = "roles") String[] roles,
                         @RequestParam(value = "username") String username,
                         @RequestParam(value = "password") String password,
                         @RequestParam(value = "age") int age) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        int[] roleInt = new int[roles.length];

        for (int i = 0; i < roles.length; i++) {
            roleInt[i] = Integer.parseInt(roles[i]);
        }
        user.setRoles(roleDao.getRolesByIds(roleInt));
        userService.updateUser(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}")
    public String serchById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "serchById";
    }


    @DeleteMapping("/admin/users/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }
}
