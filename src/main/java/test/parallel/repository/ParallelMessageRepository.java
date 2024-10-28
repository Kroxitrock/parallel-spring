package test.parallel.repository;

import org.springframework.stereotype.Repository;
import test.parallel.model.Message;
import test.parallel.model.MessageType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

@Repository
public class ParallelMessageRepository {
    private final Map<MessageType, SynchronousQueue<Message>> messageQueues;

    ParallelMessageRepository() {
        messageQueues = new HashMap<>();
        for (MessageType type: MessageType.values()) {
            messageQueues.put(type, new SynchronousQueue<>());
        }
    }


    public void sendMessage(Message message) throws InterruptedException {
        messageQueues.get(message.getType()).put(message);
    }

    public Message getNextMessage(MessageType type) throws InterruptedException {
        return messageQueues.get(type).take();
    }

}
