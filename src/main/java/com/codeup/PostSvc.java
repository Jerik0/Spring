package com.codeup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("postSvc")
public class PostSvc {
  private List<Post> posts = new ArrayList<>();

  public PostSvc() {
    createAds();
  }

  public List<Post> findAll() {
    return posts;
  }

  public Post save(Post post) {
    post.setId( (long) posts.size() + 1);
    posts.add(post);
    return post;
  }

  public Post findOne(long id) {
    return posts.get((int) (id - 1));
  }

  private void createAds() {
    save(new Post("This is the title", "This is the body"));
    save(new Post("This is also the title", "This is also the body"));
  }
}

