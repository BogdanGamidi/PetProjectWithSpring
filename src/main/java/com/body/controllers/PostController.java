package com.body.controllers;

import com.body.dto.PostOfPersonDto;
import com.body.forms.PostForm;
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
    public List<PostOfPersonDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PostOfPersonDto>> getPostsByPersonId(@PathVariable String id) {
        try {
            return new ResponseEntity<>(postService.getRepository().getPostsByPersonId(id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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