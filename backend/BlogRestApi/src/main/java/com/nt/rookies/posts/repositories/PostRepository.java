package com.nt.rookies.posts.repositories;

import com.nt.rookies.posts.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
        @Query("select p from PostEntity p where lower(p.title) like lower(concat('%', :key,'%'))")
        List<PostEntity>  findByTitleContains(@Param("key") String title);
//    List<PostEntity> findByTitleContains(String title);

}