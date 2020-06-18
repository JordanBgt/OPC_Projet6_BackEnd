package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.CommentDto;
import com.openclassrooms.escalade.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CommentMapper {

    @Mappings({
            @Mapping(source = "spot.id", target = "spotId")
    })
    CommentDto toCommentDto(Comment comment);
}
