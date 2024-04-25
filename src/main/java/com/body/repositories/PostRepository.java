package com.body.repositories;

import com.body.dto.PostOfPersonDto;
import com.body.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
    @Query(value = "select new com.body.dto.PostOfPersonDto(post.id, person.firstName, person.lastName, post.content) from Post post join Person per on per.id = post.person.id")
    List<PostOfPersonDto> getAllPostsDto();

    @Query(value = "select new com.body.dto.PostOfPersonDto(post.id, person.firstName, person.lastName, post.content) from Post post join Person per on per.id = post.person.id where per.id = :id")
    List<PostOfPersonDto> getPostsDtoByPersonId(String id);
}
