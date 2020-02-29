package org.wcci.blog.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    private String blogText;
    @ManyToOne
    private Category category;
    private String blogTitle;
    private String blogAuthor;
    @ManyToMany
    private Set<Tag> tags;

    public Blog(String blogTitle, String blogText, String blogAuthor, Category category){
        this.blogTitle = blogTitle;
        this.blogText = blogText;
        this.blogAuthor = blogAuthor;
        this.category = category;
        this.tags = new HashSet<>();
    }

    public Blog(){
    }

    public void addTag(Tag tagToAdd){
        tags.add(tagToAdd);
    }

    public Collection<Tag> getTags(){
        return tags;
    }

    public Long getId() {
        return id;
    }

    public String getBlogText() {
        return blogText;
    }

    public Category getCategory() {
        return category;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public String getBlogAuthor() {
        return blogAuthor;
    }

    @Override
    public String toString(){
        return "Blog Post: " +
                blogTitle + ", by " +
                blogAuthor + ", post ID = " +
                id + ".";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blog blog = (Blog) o;
        return Objects.equals(id, blog.id) &&
                Objects.equals(blogText, blog.blogText) &&
                Objects.equals(category, blog.category) &&
                Objects.equals(blogTitle, blog.blogTitle) &&
                Objects.equals(blogAuthor, blog.blogAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, blogText, category, blogTitle, blogAuthor, tags);
    }
}
