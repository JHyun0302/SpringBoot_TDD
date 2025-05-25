package com.example.tdd.api;

import com.example.tdd.dto.PostRequestDTO;
import com.example.tdd.dto.PostResponseDTO;
import com.example.tdd.service.PostService;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<?> postMethod(@RequestBody PostRequestDTO dto) {

        // tdd 구현
        Long resultId = postService.create(dto);

        // HTTP Body
        Map<String, Object> responseBody = Map.of("id", resultId);

        //HTTP header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json"));

        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<?> getMethod(@PathVariable("id") Long id) {
        PostResponseDTO responseDTO = postService.read(id);

        //HTTP header
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json"));

        return new ResponseEntity<>(responseDTO, httpHeaders, HttpStatus.OK);
    }
}