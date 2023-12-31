package me.stef.bluemooncity.mapper;

import me.stef.bluemooncity.entity.User;
import me.stef.bluemooncity.service.rest.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MyMapper {

    MyMapper INSTANCE = Mappers.getMapper(MyMapper.class);

    List<UserDTO> toUserDtos(List<User> in);
    UserDTO toUserDTO(User in);

    User toUser(String username, String email, String password);
}
