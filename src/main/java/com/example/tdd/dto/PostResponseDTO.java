package com.example.tdd.dto;

import com.example.tdd.entity.PostEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDTO {

    private Long id;
    private String title;
    private String content;

    public static PostResponseDTO fromEntity(PostEntity entity) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setId(entity.getId());
        postResponseDTO.setTitle(entity.getTitle());
        postResponseDTO.setContent(entity.getContent());

        return postResponseDTO;
    }
}
