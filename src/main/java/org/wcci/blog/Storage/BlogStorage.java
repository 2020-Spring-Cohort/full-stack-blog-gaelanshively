package org.wcci.blog.Storage;

import org.wcci.blog.Models.Blog;

import java.util.Collection;

public interface BlogStorage {
    Collection<Blog> findAllBlogs();

    Blog findBlogById(Long id);

    void store(Blog blogToStore);
}
