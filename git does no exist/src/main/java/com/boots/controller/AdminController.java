package com.boots.controller;

import com.boots.entity.News;
import com.boots.entity.User;
import com.boots.service.NewsService;
import com.boots.service.UserService;
import com.boots.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;

    @GetMapping("/admin/users")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @GetMapping("/admin/add")
    public String addArticle(Model model) {
        News news = new News();
        model.addAttribute("newArticle", news);
        return "addForm";
    }

    @PostMapping("/admin/add")
    public String saveArticle(@ModelAttribute("newArticle") @Valid News newsForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "addForm";
        }
        try {
            newsService.addArticle(newsForm);
        } catch (ServiceException e){
            model.addAttribute("titleError", e.getMessage());
        }

        return "redirect:/admin";
    }

    @PostMapping("/admin/users")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/gt/{userId}")
    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
    }
}
