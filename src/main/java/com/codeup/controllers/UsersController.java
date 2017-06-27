package com.codeup.controllers;

import com.codeup.Repositories.Roles;
import com.codeup.Repositories.UsersRepository;
import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UsersController {

  @Autowired
  private UsersRepository usersDao;

  @Autowired
  private Roles rolesDao;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @GetMapping("/users/create")
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "users/create";
  }

  @PostMapping("/users/create")
  public String registerUser(
          Model model,
          @Valid User user,
          Errors validation
  ) {
    if (validation.hasErrors()) {
      model.addAttribute("user", user);
      model.addAttribute("errors", validation);
      return "/users/create";
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    usersDao.save(user);
    UserRole userRole = new UserRole();
    userRole.setRole("ROLE_GUEST");
    userRole.setUserId(user.getId());
    rolesDao.save(userRole);
    return "redirect:/posts";
  }

  @GetMapping("/users/delete")
  public String deleteUser(
          @ModelAttribute User user
  ) {
    usersDao.delete(user);
    return "redirect:/posts";
  }
}
