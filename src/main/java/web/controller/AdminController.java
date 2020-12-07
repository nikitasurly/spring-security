package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public String listUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "all_users";
    }

    @GetMapping("/users/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getListRoles());
        return "add";
    }

    @PostMapping("/users")
    public String add(@ModelAttribute("user") User user,
                      @RequestParam(value = "role", required = false)
                              String[] roleFromForm) {

        Set<Role> roles = new HashSet<>();
        if (Objects.nonNull(roleFromForm)) {
            for (String role : roleFromForm) {
                roles.add(roleService.getRoleByName(role));
            }
        }
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String setUser(@PathVariable("id") Long id,
                                ModelMap model) {
        model.addAttribute("user", userService.find(id));
        model.addAttribute("allRoles", roleService.getListRoles());
        return "edit";
    }

    @PostMapping("/edit")
    public String set(@ModelAttribute("user") User user,
                      @RequestParam(value = "role", required = false)
                              String[] roleFromForm) {

        Set<Role> roles = new HashSet<>();
        if (Objects.nonNull(roleFromForm)) {
            for (String role : roleFromForm) {
                roles.add(roleService.getRoleByName(role));
            }
        }
        user.setRoles(roles);
        userService.edit(user);
        return "redirect:/admin/users";
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
