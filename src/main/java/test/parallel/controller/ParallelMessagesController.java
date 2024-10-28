package test.parallel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.parallel.model.Message;
import test.parallel.service.ParallelMessagesService;

@RestController
public class ParallelMessagesController {
    private final ParallelMessagesService parallelMessagesService;

    @Autowired
    public ParallelMessagesController(ParallelMessagesService parallelMessagesService) {
        this.parallelMessagesService = parallelMessagesService;
    }

    @PostMapping("/messages")
    public void sendMessage(@RequestBody Message message) throws InterruptedException {
        parallelMessagesService.sendMessage(message);
    }
}
