package com.ffy.blog.service.impl;

import com.ffy.blog.service.BlogIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogIssueServiceImpl implements BlogIssueService {

    @Autowired
    private BlogIssueService blogIssueService;

}
