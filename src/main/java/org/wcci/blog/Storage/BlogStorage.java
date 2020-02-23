package org.wcci.blog.Storage;

import org.wcci.blog.Models.Blog;

public interface BlogStorage {
    Blog findBlogById(Long id);

    void store(Blog blogToStore);
}
