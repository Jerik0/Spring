package com.codeup.controllers;

import com.codeup.Repositories.UsersRepository;
import com.codeup.models.Post;
import com.codeup.svcs.PostSvc;
import com.codeup.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PostsController {

  private final PostSvc postsDao;
  private final UsersRepository usersDao;

  @Autowired
  public PostsController(PostSvc postDao, UsersRepository usersDao) {
    this.postsDao = postDao;
    this.usersDao = usersDao;
  }

  //======================Viewing Posts======================
  @GetMapping("/posts") // GetMapping listens for this url, "/posts" and executes the method therein.
  public String posts(Model model) {
    Iterable<Post> posts = postsDao.findAll();
    model.addAttribute("posts", posts);
    return "posts/index"; //It then sends the added attribute to the file in the path "posts/index".
  }

  @GetMapping("/posts/{id}")
  public String postIds(@PathVariable long id, Model model) {
    model.addAttribute("post", postsDao.findOne(id));
    return "posts/show";
  }

  @GetMapping("/profile")
  public String profileRedirect(Model model) {
    Iterable<Post> posts = postsDao.findAll();
    model.addAttribute("posts", posts);
    return "users/profile";
  }

  //======================Creating Posts======================
  @GetMapping("/posts/create")
  public String savePost(Model model) {
    model.addAttribute("post", new Post());
    return "posts/create";
  }

  @PostMapping("/posts/create")
  public String savePost(
          @RequestParam(name = "title") String title,
          @RequestParam(name = "body") String body
  ) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Post post = new Post(title, body);
    post.setOwner(user);
    postsDao.save(post);
    return "redirect:/posts";
  }

  //======================Editing Posts======================
  @GetMapping("/posts/{id}/edit")
  public String showEditForm(
          @PathVariable long id,
          Model model
  ) {
    Post post = postsDao.findOne(id);
    model.addAttribute("post", post);
    return "posts/edit";
  }

  @PostMapping("/posts/{id}/edit")
  public String editPostForm(
          @ModelAttribute Post post,
          Model model
  ) {
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    post.setOwner(user);
    postsDao.save(post);
    model.addAttribute(post);
    return "redirect:/posts";
  }

  @PostMapping("/posts/delete")
  public String deletePost(
          @RequestParam(name = "id") long id
  ) {
    postsDao.deletePost(id);
    return "redirect:/profile";
  }
}
