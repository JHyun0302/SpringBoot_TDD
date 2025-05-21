package com.example.tdd.service;

import com.example.tdd.dto.PostRequestDTO;
import com.example.tdd.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long create(PostRequestDTO dto) {

//        throw new UnsupportedOperationException("구현 중");
        return 1L;

    }
}
