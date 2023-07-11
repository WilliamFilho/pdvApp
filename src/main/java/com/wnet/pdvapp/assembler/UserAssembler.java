package com.wnet.pdvapp.assembler;

import com.wnet.pdvapp.dto.UserDTO;
import com.wnet.pdvapp.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserAssembler {
    private ModelMapper modelMapper;

    public UserDTO toModel(User user) {
        return modelMapper.map(user, UserDTO.class);
    }


    //qdo precisar de um List...
    public List<UserDTO> toCollectionModel(List<User> users) {
        return users.stream().map(this::toModel).collect(Collectors.toList());
    }

    //qdo precisar de um List paginado...
    public Page<UserDTO> toCollectionModelPage(Page<User> users) {
        return users.map(this::toModel);
    }

    //de DTO para Entity
    public User toEntity(UserDTO dto){
        return modelMapper.map(dto, User.class);
    }
}
