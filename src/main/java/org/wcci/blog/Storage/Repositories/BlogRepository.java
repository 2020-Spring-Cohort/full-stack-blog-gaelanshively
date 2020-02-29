package org.wcci.blog.Storage.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.Models.Blog;

import java.util.Optional;

public interface BlogRepository extends CrudRepository<Blog, Long> {
    Optional<Blog> findById(Long blogToFind);
}
