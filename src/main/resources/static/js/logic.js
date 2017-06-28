"use strict";

(() => {

  let request = $.ajax({
    'url': '/posts.json'
  });

  // Responds to /posts/ajax URL. Creates individual divs with ajax information inside of them.
  request.done((posts) => {
    console.log(posts); //array of posts
    let html = '';
    posts.forEach((post) => {
      html += '<div class="blog-post">';
      html += '<h1>' + post.title + '</h1>';
      html += '<p>' + post.body + '</p>';
      html += '<h5>By: ' + post.owner.username + '</h5>';
      html += '</div>'
    });
    $('#ajax-posts').html(html);
  });

})();