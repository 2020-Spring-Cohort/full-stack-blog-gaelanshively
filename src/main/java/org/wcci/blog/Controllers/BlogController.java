package org.wcci.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wcci.blog.Models.Blog;
import org.wcci.blog.Storage.BlogStorage;

@Controller
public class BlogController {
    private final BlogStorage blogStorage;

    public BlogController(BlogStorage blogStorage){
        this.blogStorage = blogStorage;
    }

    @RequestMapping("/blog/{id}")
    public String displayBlog(@PathVariable Long id, Model model){
        Blog retrievedBlog = blogStorage.findBlogById(id);
        model.addAttribute("blog", retrievedBlog);
        return "blog-view";
    }
}
