package test.parallel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.parallel.model.Message;
import test.parallel.model.MessageType;
import test.parallel.repository.ParallelMessageRepository;
import test.parallel.thread.SendThread;

@Service
public class ParallelMessagesService {

    private final ParallelMessageRepository parallelMessageRepository;

    @Autowired
    public ParallelMessagesService(ParallelMessageRepository parallelMessageRepository) {
        this.parallelMessageRepository = parallelMessageRepository;
        for (MessageType type: MessageType.values()) {
            Thread thread = new Thread(new SendThread(type, this));
            thread.start();
        }
    }

    public void sendMessage(Message message) throws InterruptedException {
        switch (message.getType()) {
            case SMS -> System.out.println("SMS message received!");
            case VIBER -> System.out.println("Viber message received!");
            case WHATSAPP -> System.out.println("WHATSAPP message received!");
        }

        parallelMessageRepository.sendMessage(message);
    }

    public Message getNextMessage(MessageType messageType) throws InterruptedException {
        return parallelMessageRepository.getNextMessage(messageType);
    }
}
