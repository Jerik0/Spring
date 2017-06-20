package com.codeup.controllers;

import com.codeup.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostsController {

    // When URL Pattern is "/posts", adds new post to Array List.
    // Will be updated later to use MySQL insertions.
    @GetMapping("/posts")
    public String posts(Model model) {
        ArrayList<Post> postArrayList = new ArrayList<>();

        postArrayList.add(new Post("This is the title", "This is the body"));
        postArrayList.add(new Post("This is also the title", "This is also the body"));

        model.addAttribute("posts", postArrayList);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postIds(@PathVariable long id, Model model) {

        Post post = new Post("Blah", "This is the body of the post");

        model.addAttribute("post", post);
        return "posts/show";
    }

//    @PostMapping("/posts/create")
//    public String viewPostForm() {
//        return String.format("%s", "view the form for creating a post");
//    }
}
