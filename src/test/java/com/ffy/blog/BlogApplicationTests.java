package com.ffy.blog;

import com.ffy.blog.dao.TagRepository;
import com.ffy.blog.po.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private TagRepository tagRepository;

    @Test
    void contextLoads() {
        List<Long> id = new ArrayList<>();
        id.add(1l);
        id.add(2l);
        id.add(3l);
        List<Tag> in = tagRepository.findTagByIdIn(id);
        for (Tag tag : in) {
            System.out.println(tag);
        }
    }

}
