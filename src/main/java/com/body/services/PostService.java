package com.body.services;

import com.body.dto.PostOfPersonDto;
import com.body.forms.PostForm;
import com.body.models.Post;
import com.body.repositories.PostRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final Logger logger = LogManager.getLogger(getClass());
    private final PostRepository postRepository;

    public PostService(@Autowired PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostRepository getRepository() {
        return postRepository;
    }

    // admin call
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    // user call
    public List<PostOfPersonDto> getAllPostsDto() {
        return postRepository.getAllPostsDto();
    }

    // user call
    public List<PostOfPersonDto> getPostsDtoByPersonId(String id) {
        List<PostOfPersonDto> postsOfPersonDto = null;
        try {
            postsOfPersonDto = postRepository.getPostsDtoByPersonId(id);
        } catch (Exception e) {
            logger.error("Person with id {} not found", id);
        }
        return postsOfPersonDto;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(String id, PostForm postForm) {
        Optional<Post> post = postRepository.findById(id);
        if (postForm.content != null) post.get().setContent(postForm.content);
        return postRepository.save(post.get());
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
}