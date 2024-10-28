package test.parallel.thread;

import test.parallel.model.Message;
import test.parallel.model.MessageType;
import test.parallel.service.ParallelMessagesService;

public class SendThread implements Runnable {

    private final MessageType type;
    private final ParallelMessagesService parallelMessagesService;

    public SendThread(MessageType type, ParallelMessagesService parallelMessagesService) {
        this.type = type;
        this.parallelMessagesService = parallelMessagesService;

    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + " is running for message type " + type.name());

        while(true) {
            try {
                Message message = parallelMessagesService.getNextMessage(type);

                Thread.sleep(2000); // long operation

                System.out.println("Successfully sent " + type.name() + " message " + message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
