package com.ensam.pharmafind.dto.mappers;

import com.ensam.pharmafind.dto.responses.RoleDTO;
import com.ensam.pharmafind.dto.responses.UserDTO;
import com.ensam.pharmafind.entities.Role;
import com.ensam.pharmafind.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO toUserDTO(User user);
    RoleDTO toRoleDTO(Role role);
}
