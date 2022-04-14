package com.post.malone.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestAPI 용 컨트롤러, JSON 을 반환
public class FirstApiController {

    @GetMapping("/api/donda")
    public String donda() {
        return "Kanye West";
    }
}
