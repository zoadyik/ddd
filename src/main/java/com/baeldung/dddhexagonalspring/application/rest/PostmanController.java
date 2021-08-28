package com.baeldung.dddhexagonalspring.application.rest;

import com.baeldung.dddhexagonalspring.domain.service.PostmanEchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/postmans")
public class PostmanController {

    private static final Logger log = LoggerFactory.getLogger(PostmanController.class);

    private final PostmanEchoService postmanEchoService;

    @Autowired
    public PostmanController(PostmanEchoService postmanEchoService) {
        this.postmanEchoService = postmanEchoService;
    }

    @GetMapping()
    public ResponseEntity<Object> getEcho(@RequestParam Optional<String> request) {
        log.info("postman echo get => {}", getRequest(request));
        return ResponseEntity.ok(postmanEchoService.getEcho(getRequest(request)));
    }

    @PostMapping()
    public ResponseEntity<Object> postEcho(@RequestParam Optional<String> request, @RequestBody final Object body) {
        log.info("postman echo post => request {}, body {}", getRequest(request), body);
        return ResponseEntity.ok(postmanEchoService.postEcho(getRequest(request), body));
    }

    private String getRequest(@RequestParam Optional<String> request) {
        return request.orElse("");
    }
}
