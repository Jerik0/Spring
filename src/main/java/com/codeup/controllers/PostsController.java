package com.codeup.controllers;

import com.codeup.Repositories.PostsRepository;
import com.codeup.models.Post;
import com.codeup.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostsController {

  private final PostSvc postsDao;

  @Autowired
  public PostsController(PostSvc postDao) {
    this.postsDao = postDao;
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
    Post post = new Post(title, body);
    postsDao.save(post);
    return "redirect:/posts";
  }
  //======================Editing Posts======================
  @GetMapping("/posts/{id}/edit")
  public String showEditForm(@PathVariable long id, Model model) {
    Post post = postsDao.findOne(id);
    model.addAttribute("post", post);
    return "posts/edit";
  }

  @PostMapping("/posts/{id}/edit")
  public String editPostForm(@ModelAttribute Post post, Model model) {
    postsDao.save(post);
    model.addAttribute(post);
    return "redirect:/posts";
  }

  @PostMapping("/posts/delete")
  public String deletePost(@RequestParam(name = "id") long id) {
    postsDao.deletePost(id);
    return "redirect:/posts";
  }
}
