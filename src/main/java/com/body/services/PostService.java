package com.body.services;

import com.body.forms.PostForm;
import com.body.models.Post;
import com.body.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(@Autowired PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostRepository getRepository() {
        return postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, PostForm postForm) {
        Optional<Post> post = postRepository.findById(id);

        if (postForm.title != null) post.get().setTitle(postForm.title);
        if (postForm.content != null) post.get().setContent(postForm.content);
        return postRepository.save(post.get());
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}