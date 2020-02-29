package org.wcci.blog.Storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.Models.Blog;
import org.wcci.blog.Storage.Repositories.BlogRepository;

@Service
public class BlogStorageJpa implements BlogStorage{
    private final BlogRepository blogRepository;

    public BlogStorageJpa(BlogRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog findBlogById(Long id){
        return blogRepository.findById(id).get();
    }

    @Override
    public void store(Blog blogToStore){
        blogRepository.save(blogToStore);
    }

}
