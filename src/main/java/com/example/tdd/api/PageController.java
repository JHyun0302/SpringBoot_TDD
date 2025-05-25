package com.example.tdd.api;

import com.example.tdd.dto.PostRequestDTO;
import com.example.tdd.entity.PostEntity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @GetMapping("/page")
    public String getPage(Model model) {
        List<PostEntity> postEntityList = new ArrayList<>();
        model.addAttribute("POSTLIST", postEntityList);

        return "page";
    }

    @PostMapping("/page")
    public String postPage(PostRequestDTO dto) {

        return "redirect:/page";
    }
}
