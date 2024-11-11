package com.myblog2.Repository;

import com.myblog2.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long > {
}
