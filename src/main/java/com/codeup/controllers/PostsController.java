package com.codeup.controllers;

import com.codeup.Post;
import com.codeup.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostsController {

  private final PostSvc postSvc;

  @Autowired
  public PostsController(PostSvc postSvc) {
    this.postSvc = postSvc;
  }

  // When URL Pattern is "/posts", adds new post to Array List.
  // Will be updated later to use MySQL insertions.
  @GetMapping("/posts")
  public String posts(Model model) {

    model.addAttribute("posts", postSvc.findAll());
    return "posts/index";
  }

  @GetMapping("/posts/{id}")
  public String postIds(@PathVariable long id, Model model) {

    model.addAttribute("post", postSvc.findOne(id));
    return "posts/show";
  }

  @GetMapping("/posts/create")
  public String savePost(Model model) {
    model.addAttribute("post", new Post());
    return "posts/create";
  }

  @PostMapping("/posts/create")
  public String viewPostForm(
          Model model,
          @RequestParam(name = "title") String title,
          @RequestParam(name = "body") String body
  ) {
    Post post = new Post(title, body);
    model.addAttribute(post);
    return "posts/create";
  }

  @GetMapping("/posts/edit")
  public String EditPost(Model model) {
    model.addAttribute("post", new Post());
    return "posts/edit";
  }

  @PostMapping("/posts/edit")
  public String editPostForm(
          Model model,
          @RequestParam(name = "title") String title,
          @RequestParam(name = "body") String body
  ) {
    Post post = new Post(title, body);
    postSvc.save(post);
    model.addAttribute(post);
    return "posts/edit";
  }
}
