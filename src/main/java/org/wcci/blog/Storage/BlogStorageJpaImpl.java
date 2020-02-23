package org.wcci.blog.Storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.Models.Blog;
import org.wcci.blog.Storage.Repositories.BlogRepository;

@Service
public class BlogStorageJpaImpl implements BlogStorage{
    private final BlogRepository blogRepository;

    public BlogStorageJpaImpl(BlogRepository blogRepository){
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
