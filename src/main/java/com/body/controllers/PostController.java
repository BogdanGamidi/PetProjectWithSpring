package com.body.controllers;

import com.body.forms.PostForm;
import com.body.models.Post;
import com.body.services.PersonService;
import com.body.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final PersonService personService;

    public PostController(@Autowired PostService postService, @Autowired PersonService personService) {
        this.postService = postService;
        this.personService = personService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        try {
            Post post = postService.getPostById(id);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Optional<Post> createPost(@RequestBody Post post) {
        if (personService.getPersonById(post.getPersonId().getId()) == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(postService.createPost(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostForm postForm) {
        try {
            Post post = postService.updatePost(id, postForm);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        if (postService.getPostById(id) == null) {
            return "Post not found";
        }
        postService.deletePost(id);
        return "Post was deleted";
    }
}
