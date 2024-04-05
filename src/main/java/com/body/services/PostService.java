package com.body.services;

import com.body.forms.PostForm;
import com.body.forms.PostOfPerson;
import com.body.models.Person;
import com.body.models.Post;
import com.body.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // admin call
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    // user call
    public List<Object> getAllPosts() {
//        List<Post> posts = postRepository.findAll();
//        List<PostOfPerson> postOfPerson = new ArrayList<>();
//        for (int i = 0; i < posts.size(); ++i) {
//            PostOfPerson post = new PostOfPerson();
//            post.firstName = posts.get(i).getPerson().getFirstName();
//            post.lastName = posts.get(i).getPerson().getLastName();
//            post.title = posts.get(i).getTitle();
//            post.content = posts.get(i).getContent();
//            postOfPerson.add(post);
//        }
//        return postOfPerson;
        return postRepository.getAllPosts();
    }

    // user call
    public List<Object> getPostsByFirstNameOfPerson(String firstName) {
        return postRepository.getPostsByFirstNameOfPerson(firstName);
    }

    // user call
    public List<Object> getPostsByFirstAndLastNameOfPerson(String firstName, String lastName) {
        return postRepository.getPostsByFirstAndLastNameOfPerson(firstName, lastName);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(String id, PostForm postForm) {
        Optional<Post> post = postRepository.findById(id);

        if (postForm.title != null) post.get().setTitle(postForm.title);
        if (postForm.content != null) post.get().setContent(postForm.content);
        return postRepository.save(post.get());
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
}