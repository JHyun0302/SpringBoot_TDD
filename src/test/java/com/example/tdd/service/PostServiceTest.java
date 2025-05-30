package com.example.tdd.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

import com.example.tdd.dto.PostRequestDTO;
import com.example.tdd.entity.PostEntity;
import com.example.tdd.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    PostService postService;

    @Mock
    PostRepository postRepository;

    @Test
    void create_테스트1() {
        // given
        PostRequestDTO requestDTO = new PostRequestDTO();
        requestDTO.setTitle("제목1");
        requestDTO.setContent("내용1");

        PostEntity saved = new PostEntity();
        ReflectionTestUtils.setField(saved, "id", 1L);
        saved.setTitle("제목");
        saved.setContent("내용");
        given(postRepository.save(any(PostEntity.class))).willReturn(saved);

        // when
        Long resultId = postService.create(requestDTO);

        // then
        assertTrue(resultId instanceof Long);

    }

    @Test
    void create_테스트2() {
        // given
        PostRequestDTO requestDTO = new PostRequestDTO();
        requestDTO.setTitle("제목2");
        requestDTO.setContent("내용2");

        PostEntity saved = new PostEntity();
        ReflectionTestUtils.setField(saved, "id", 1L);
        saved.setTitle("제목");
        saved.setContent("내용");
        given(postRepository.save(any(PostEntity.class))).willReturn(saved);

        // when
        postService.create(requestDTO);

        // then
        verify(postRepository).save(any(PostEntity.class));
    }

    @Test
    void create_테스트3() {
        //given
        PostRequestDTO requestDTO = new PostRequestDTO();
        requestDTO.setTitle("");
        requestDTO.setContent("내용");

        //when & then
        assertThrows(IllegalArgumentException.class, () ->{
            postService.create(requestDTO);
        });
    }
}