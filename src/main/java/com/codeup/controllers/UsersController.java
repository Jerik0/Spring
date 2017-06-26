package com.codeup.controllers;

import com.codeup.Repositories.UsersRepository;
import com.codeup.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

  @Autowired
  private UsersRepository usersDao;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping("/users/create")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "users/create";
  }

  @PostMapping("/users/create")
  public String registerUser(@ModelAttribute User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    usersDao.save(user);
    return "redirect:/posts";
  }

  @GetMapping("/users/delete")
  public void deleteUser(@ModelAttribute User user) {
    usersDao.delete(user);
  }
}
