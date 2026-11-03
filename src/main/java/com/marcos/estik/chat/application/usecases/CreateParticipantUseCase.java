package com.marcos.estik.chat.application.usecases;

import org.springframework.stereotype.Service;

import com.marcos.estik.chat.application.command.CreateParticipantCommand;
import com.marcos.estik.chat.application.ports.in.CreateParticipantPort;
import com.marcos.estik.chat.application.ports.out.ChatRepositoryPort;
import com.marcos.estik.chat.application.ports.out.LoadChatFindUserByIdPort;
import com.marcos.estik.chat.application.ports.out.ParticipantRepositoryPort;
import com.marcos.estik.chat.application.view.ChatParticipantInfo;
import com.marcos.estik.chat.application.view.ChatUserInfo;
import com.marcos.estik.chat.domain.Chat;
import com.marcos.estik.chat.domain.Participant;
import com.marcos.estik.chat.domain.exception.ParticipantAlreadyExistsException;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CreateParticipantUseCase implements CreateParticipantPort{

    private final ChatRepositoryPort chatRepositoryPort;
    private final ParticipantRepositoryPort participantRepositoryPort;
    private final LoadChatFindUserByIdPort loadChatFindUserByIdPort;

    @Override
    public ChatParticipantInfo execute(CreateParticipantCommand command) {
        Chat chat = chatRepositoryPort.findById(command.chatId());
        ChatUserInfo user = loadChatFindUserByIdPort.execute(command.userId());
        if (!participantRepositoryPort.existsByUserIdAndChatId(user.id(), chat.getId())) {
            throw new ParticipantAlreadyExistsException("user already in chat");
        }
        
        Participant participant = Participant.create(chat.getId(), user.id());
        participantRepositoryPort.save(participant);

        return new ChatParticipantInfo(
            participant.getId(),
            participant.getChatId(),
            user,
            participant.getJoinedAt()
        );
    }
}
