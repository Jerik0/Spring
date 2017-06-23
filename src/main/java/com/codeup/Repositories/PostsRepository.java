package com.codeup.Repositories;

import com.codeup.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Post, Long> {

}
