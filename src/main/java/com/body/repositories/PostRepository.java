package com.body.repositories;

import com.body.forms.PostOfPerson;
import com.body.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    @Query(value = "select person.first_name, person.last_name, post.title, post.content from post join person on person.id = post.person_id", nativeQuery = true)
    List<Object> getAllPosts(); // <firstName, lastName, title, content>

    @Query(value = "select person.first_name, person.last_name, post.title, post.content from post join person on person.id = post.person_id where person.first_name = ?1", nativeQuery = true)
    List<Object> getPostsByFirstNameOfPerson(String firstName);

    @Query(value = "select person.first_name, person.last_name, post.title, post.content from post join person on person.id = post.person_id where person.first_name = ?1 and person.last_name = ?2", nativeQuery = true)
    List<Object> getPostsByFirstAndLastNameOfPerson(String firstName, String lastName);
}
