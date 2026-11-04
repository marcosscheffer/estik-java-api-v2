package com.marcos.estik.chat.adapter.out.persistence.attachment;

import org.mapstruct.Mapper;

import com.marcos.estik.chat.domain.Attachment;

@Mapper(componentModel = "spring")
public interface AttachmentPersistenceMapper {

    Attachment toDomain(AttachmentEntity attachment);

    AttachmentEntity toEntity(Attachment attachment);

}
