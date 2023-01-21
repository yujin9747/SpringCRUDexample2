package com.example.springcrudexample2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/adduserform")
    public String adduserform(){
        return "adduserform";
    }

    @PostMapping("/adduser")
    public String adduser(UserForm form){
        User user = new User();
        user.setName(form.getName());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());
        user.setSex(form.getSex());
        user.setCountry(form.getCountry());
        int result = userService.save(user);
        if(result > 0) return "adduser-success";
        else return "adduser-error";
    }

    @RequestMapping("/viewusers")
    public String viewusers(Model model){
        List<User> result = userService.getAll();
        model.addAttribute("users", result);
        return "viewusers";
    }

    @RequestMapping("/editform")
    public String editform(@RequestParam("id") int id, Model model){
        User user = userService.getOne(id);
        model.addAttribute("user", user);
        return "edituserform";
    }

    @PostMapping("/edituser")
    public String edituser(UserForm form){
        User user = new User();
        user.setName(form.getName());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());
        user.setSex(form.getSex());
        user.setCountry(form.getCountry());
        int result = userService.edit(user);
        System.out.println("Edit result: " + result);
        return "redirect:/viewusers";
    }

    @RequestMapping("deleteuser")
    public String deleteuser(@RequestParam("id") int id){
        userService.delete(id);
        return "redirect:/viewusers";
    }
}
