package com.example.tdd.service;

import com.example.tdd.dto.PostRequestDTO;
import com.example.tdd.dto.PostResponseDTO;
import com.example.tdd.entity.PostEntity;
import com.example.tdd.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long create(PostRequestDTO dto) {

        if (dto.getTitle().isBlank()) {
            throw new IllegalArgumentException("빈값 금지");
        }

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(dto.getTitle());
        postEntity.setContent(dto.getContent());

//        throw new UnsupportedOperationException("구현 중");
        return postRepository.save(postEntity).getId();

    }

    public PostResponseDTO read(Long id) {
        return PostResponseDTO.fromEntity(postRepository.findById(id).get());
    }
}
