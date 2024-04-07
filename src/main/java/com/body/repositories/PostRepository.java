package com.body.repositories;

import com.body.dto.PostOfPersonDto;
import com.body.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    @Query("select new com.body.dto.PostOfPersonDto(post.id, person.firstName, person.lastName, post.title, post.content) from Post post join Person person on person.id = post.person.id")
    List<PostOfPersonDto> getAllPosts();

    @Query("select new com.body.dto.PostOfPersonDto(post.id, person.firstName, person.lastName, post.title, post.content) from Post post join Person person on person.id = post.person.id where person.id = :id")
    List<PostOfPersonDto> getPostsByPersonId(String id);
}
