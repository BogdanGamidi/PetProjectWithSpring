package com.body.controllers;

import com.body.forms.PostForm;
import com.body.forms.PostOfPerson;
import com.body.models.Post;
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

    public PostController(@Autowired PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/admin")
    public List<Post> findAllPosts() {
        return postService.findAllPosts();
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        Optional<Post> post = postService.getRepository().findById(id);
        if (post.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post.get(), HttpStatus.OK);
    }

    @GetMapping
    public List<Object> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<List<Object>> getPostsByFirstNameOfPerson(@PathVariable String firstName) {
        Optional<List<Object>> post = Optional.ofNullable(postService.getRepository().getPostsByFirstNameOfPerson(firstName));
        if (post.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post.get(), HttpStatus.OK);
    }

    @GetMapping("/{firstName}/{lastName}")
    public ResponseEntity<List<Object>> getPostsByFirstAndLastNameOfPerson(@PathVariable String firstName, @PathVariable String lastName) {
        Optional<List<Object>> post = Optional.ofNullable(postService.getRepository().getPostsByFirstAndLastNameOfPerson(firstName, lastName));
        if (post.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody PostForm postForm) {
        Optional<Post> post = postService.getRepository().findById(id);
        if (post.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postService.updatePost(id, postForm), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        try {
            postService.deletePost(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}