package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostsController {

    @GetMapping("/posts")
    @ResponseBody
    public String posts() {
        return String.format("%s", "posts index page");
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String postIds(@PathVariable int id) {
        return String.format("%s", "view an individual post");
    }

//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String viewPostForm() {
//        return String.format("%s", "view the form for creating a post");
//    }
}
