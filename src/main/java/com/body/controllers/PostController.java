package com.body.controllers;

import com.body.forms.PostForm;
import com.body.models.Post;
import com.body.services.PersonService;
import com.body.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Post> getPostById(@PathVariable Long id) {
        if (postService.getPostById(id) == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(postService.getPostById(id));
    }

    @PostMapping
    public Optional<Post> createPost(@RequestBody Post post) {
        if (personService.getPersonById(post.getPersonId().getId()) == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(postService.createPost(post));
    }

    @PutMapping("/{id}")
    public Optional<Post> updatePost(@PathVariable Long id, @RequestBody PostForm postForm) {
        if (postService.getPostById(id) == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(postService.updatePost(id, postForm));
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
