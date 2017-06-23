package com.codeup.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "post")
public class Post {
  //=================TABLE/MODEL INFORMATION=================
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false, columnDefinition = "text")
  private String body;

  @OneToOne
  private User owner;

  @ManyToOne
  @JoinColumn(name = "post_id")
  private Post post;

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

}
