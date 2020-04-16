package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.CommentDto;
import com.openclassrooms.escalade.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SpotMapper.class)
public interface CommentMapper {

    @Mapping(source = "spot.id", target = "spotId")
    CommentDto toCommentDto(Comment comment);
}
