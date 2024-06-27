package com.ensam.pharmafind.dto.mappers;

import com.ensam.pharmafind.dto.responses.RoleDTO;
import com.ensam.pharmafind.dto.responses.UserDTO;
import com.ensam.pharmafind.entities.Role;
import com.ensam.pharmafind.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImp {
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.firstName( user.getFirstName() );
        userDTO.lastName( user.getLastName() );
        userDTO.email( user.getEmail() );
        userDTO.role( toRoleDTO( user.getRole() ) );

        return userDTO.build();
    }

    public RoleDTO toRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO.RoleDTOBuilder roleDTO = RoleDTO.builder();

        roleDTO.name( role.getName() );

        return roleDTO.build();
    }
}
