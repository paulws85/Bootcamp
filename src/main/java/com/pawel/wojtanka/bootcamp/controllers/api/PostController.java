package com.pawel.wojtanka.bootcamp.controllers.api;

import com.pawel.wojtanka.bootcamp.dto.Post;
import com.pawel.wojtanka.bootcamp.model.PostRest;
import com.pawel.wojtanka.bootcamp.service.rest.PostRestService;
import com.pawel.wojtanka.bootcamp.service.rest.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRestService postRestService;

    @ResponseBody
    @GetMapping("/rest-template/post/all")
    public List<Post> getPost() {
        return postService.getPosts();
    }

    @GetMapping("/api/rest-template/post/create")
    public String showPost(Model model) {
        model.addAttribute("postRest", PostRest.builder().build());

        return "post/add-post";
    }

    @PostMapping("/api/rest-template/post-adding/summary")
    public String createPost(@ModelAttribute PostRest postRest, Model model) {
        PostRest createdPost = postRestService.addPost(postRest);
        model.addAttribute("createdPost", createdPost);

        return "post/summary";
    }


}
