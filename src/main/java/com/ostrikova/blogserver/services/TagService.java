package com.ostrikova.blogserver.services;

import com.ostrikova.blogserver.model.Article;
import com.ostrikova.blogserver.model.Tag;
import com.ostrikova.blogserver.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag createTag(String tagName) {
        Tag checkedTag = tagRepository.findByName(tagName);

        if (checkedTag == null) {
            Tag tag = new Tag(tagName);
            return tagRepository.save(tag);
        }

        return checkedTag;
    }

    public List<Tag> getTags() {
        Iterable<Tag> tagIterable = tagRepository.findAll();
        List<Tag> tags = new ArrayList<>();

        for (Tag tag : tagIterable) {
            tags.add(tag);
        }

        return tags;
    }
}
