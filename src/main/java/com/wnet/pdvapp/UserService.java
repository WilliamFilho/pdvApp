package com.wnet.pdvapp;

import com.wnet.pdvapp.assembler.UserAssembler;
import com.wnet.pdvapp.dto.UserDTO;
import com.wnet.pdvapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserAssembler assembler;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll(){
        return assembler.toCollectionModel(repository.findAll());
    }
}
