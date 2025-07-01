package com.nebula.Nebula.community.service;

import com.nebula.Nebula.community.repo.PostRepo;
import com.nebula.Nebula.community.repo.ReplyRepo;
import com.nebula.Nebula.community.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private TagRepo tagRepo;

    @Autowired
    private ReplyRepo replyRepo;
}
