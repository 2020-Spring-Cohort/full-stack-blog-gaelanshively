package org.wcci.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wcci.blog.Models.Blog;
import org.wcci.blog.Models.Category;
import org.wcci.blog.Models.Tag;
import org.wcci.blog.Storage.BlogStorage;
import org.wcci.blog.Storage.CategoryStorage;
import org.wcci.blog.Storage.Repositories.TagRepository;

import java.util.Optional;

@Controller
public class BlogController {
    private BlogStorage blogStorage;
    private CategoryStorage categoryStorage;
    private TagRepository tagRepository;

    public BlogController(BlogStorage blogStorage, CategoryStorage categoryStorage, TagRepository tagRepository){
        this.blogStorage = blogStorage;
        this.categoryStorage = categoryStorage;
        this.tagRepository = tagRepository;
    }

    @RequestMapping("/blog/{id}")
    public String displayBlog(@PathVariable Long id, Model model){
        Blog retrievedBlog = blogStorage.findBlogById(id);
        model.addAttribute("blog", retrievedBlog);
        return "blog-view";
    }

    @PostMapping("/add-blog")
    public String addBlog(@RequestParam String blogTitle, @RequestParam String blogText, @RequestParam String blogAuthor, @RequestParam String categoryName){
        Category blogCategory = categoryStorage.findCategoryByName(categoryName);
        blogStorage.store(new Blog(blogTitle, blogText, blogAuthor, blogCategory));
        return "redirect:categories/" + categoryName;
    }

    @PostMapping("/blog/{id}/add-tag")
    public String addTagToBlog(@RequestParam String name, @PathVariable Long id){
        Tag tagToAddToBlog;
        Optional<Tag> tagOptional = tagRepository.findByName(name);

        if (tagOptional.isEmpty()){
            tagToAddToBlog = new Tag(name);
            tagRepository.save(tagToAddToBlog);
        } else {
            tagToAddToBlog = tagOptional.get();
        }

        Blog blogToTag = blogStorage.findBlogById(id);
        blogToTag.addTag(tagToAddToBlog);
        blogStorage.store(blogToTag);
        return "redirect:/blog/" + id;
    }
}
