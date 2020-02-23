package org.wcci.blog.Storage.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.Models.Blog;

public interface BlogRepository extends CrudRepository<Blog, Long> {
}
