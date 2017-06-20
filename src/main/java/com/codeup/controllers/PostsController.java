package com.codeup.controllers;

import com.codeup.Post;
import com.codeup.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

//    @PostMapping("/posts/create")
//    public String viewPostForm() {
//        return String.format("%s", "view the form for creating a post");
//    }
}
