package org.wcci.blog.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    public Blog(String blogTitle, String blogText, String blogAuthor, Category category){
        this.blogTitle = blogTitle;
        this.blogText = blogText;
        this.blogAuthor = blogAuthor;
        this.category = category;
    }

    public Blog(){
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
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Blog)) return false;

        Blog blog = (Blog) o;

        if (getId() != null ? !getId().equals(blog.getId()) : blog.getId() != null) return false;
        if (getBlogText() != null ? !getBlogText().equals(blog.getBlogText()) : blog.getBlogText() != null) return false;
        if (getBlogAuthor() != null ? !getBlogAuthor().equals(blog.getBlogAuthor()) : blog.getBlogAuthor() != null) return false;
        if (getCategory() != null ? !getCategory().equals(blog.getCategory()) : blog.getCategory() != null) return false;
        return getBlogTitle() != null ? getBlogTitle().equals(blog.getBlogText()) : blog.getBlogTitle() == null;
    }

    @Override
    public int hashCode(){
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getBlogText() != null ? getBlogText().hashCode() : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        result = 31 * result + (getBlogTitle() != null ? getBlogTitle().hashCode() : 0);
        return result;
    }




}
