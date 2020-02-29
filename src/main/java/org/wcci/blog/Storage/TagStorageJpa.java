package org.wcci.blog.Storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.Models.Tag;

import java.util.Collection;

@Service
public class TagStorageJpa implements TagStorage{
    private TagRepository tagRepository;

    public TagStorageJpa(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    @Override
    public Collection<Tag> findAllTags() {
        return (Collection<Tag>) tagRepository.findAll();
    }

    @Override
    public void storeTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public Tag findTagByName(String tagName) {
        return tagRepository.findByName(tagName).get();
    }
}
