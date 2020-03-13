package com.openclassrooms.escalade.mapper;

import com.openclassrooms.escalade.dto.CommentDto;
import com.openclassrooms.escalade.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CommentMapper {

    @Mapping(source = "user.id", target = "userId")
    CommentDto toCommentDto(Comment comment);
    List<CommentDto> toListCommentDto(List<Comment> comments);
}
