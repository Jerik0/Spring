package com.codeup.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "posts")
public class Post {
  //=================TABLE/MODEL INFORMATION=================
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 100)
  @NotBlank(message = "Title can't be blank.")
  @Size(min = 3, message = "Title must be at least 3 characters.")
  private String title;

  @Column(nullable = false, columnDefinition = "text")
  @NotBlank(message = "Body cannot be empty. Go ahead, impress someone.")
  private String body;

  @ManyToOne
  @JoinColumn(name="owner_id")
  private User owner;

  //=================CONSTRUCTORS, GETTERS AND SETTERS=================
  public Post() {
  }

  public Post(String title, String body) {
    this.title = title;
    this.body = body;
  }

  public Post(long id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getBody() {
    return this.body;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }
}
