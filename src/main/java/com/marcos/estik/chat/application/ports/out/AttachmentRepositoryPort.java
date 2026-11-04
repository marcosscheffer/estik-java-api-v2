package com.marcos.estik.chat.application.ports.out;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.marcos.estik.chat.domain.Attachment;

public interface AttachmentRepositoryPort {

    Attachment save(Attachment attachment);

    List<Attachment> findByMessageIds(Collection<UUID> messageIds);

}
