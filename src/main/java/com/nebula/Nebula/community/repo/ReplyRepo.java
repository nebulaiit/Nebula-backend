package com.nebula.Nebula.community.repo;

import com.nebula.Nebula.community.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReplyRepo extends JpaRepository<Reply, UUID> {}