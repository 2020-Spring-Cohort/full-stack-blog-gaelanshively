package org.wcci.blog.Storage;

import org.wcci.blog.Models.Tag;

import java.util.Collection;

public interface TagStorage {
    Collection<Tag> findAllTags();

    void storeTag(Tag tag);

    Tag findTagByName(String tagName);
}
