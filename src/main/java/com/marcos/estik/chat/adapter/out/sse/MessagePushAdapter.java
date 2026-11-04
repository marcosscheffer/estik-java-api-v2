package com.marcos.estik.chat.adapter.out.sse;

import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.marcos.estik.chat.adapter.in.sse.SseChatEmitter;
import com.marcos.estik.chat.application.ports.out.MessagePushPort;
import com.marcos.estik.chat.application.view.MessageView;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class MessagePushAdapter implements MessagePushPort {

    private final MessageSseMapper messageSseMapper;
    
    @Override
    public void execute(MessageView message) {
        MessagePayload payload = messageSseMapper.toPayload(message);
        Runnable send = () -> SseChatEmitter.sendTo(message.message().getChatId(), "message", payload);

        if(TransactionSynchronizationManager.isSynchronizationActive()) {
            TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronization() {
                    @Override
                    public void afterCommit() {
                        send.run();
                    }
                }
            );
        } else {
            send.run();
        }
    }
}
