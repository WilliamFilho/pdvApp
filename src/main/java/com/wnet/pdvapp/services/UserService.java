package com.wnet.pdvapp.services;

import com.wnet.pdvapp.assembler.UserAssembler;
import com.wnet.pdvapp.dto.UserDTO;
import com.wnet.pdvapp.entity.User;
import com.wnet.pdvapp.entity.exceptions.EntidadeEmUsoException;
import com.wnet.pdvapp.entity.exceptions.EntidadeNaoEncontradaException;
import com.wnet.pdvapp.entity.exceptions.UserNaoEncontradoException;
import com.wnet.pdvapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.TemporalAdjuster;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserAssembler assembler;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return assembler.toCollectionModel(repository.findAll());
    }

    /*
    public ResponseEntity<UserDTO> insert(UserDTO dto){
        try {
            User newUser = assembler.toEntity(dto);
            newUser.setEnable(true);
            repository.save(newUser);
            return ResponseEntity.ok(assembler.toModel(newUser));
        }catch (Exception error){
            return ResponseEntity.notFound().build();
        }
    }
   */
    @Transactional
    public UserDTO salvar(UserDTO dto) {
        User newUser = assembler.toEntity(dto);
        newUser.setEnable(true);
        repository.save(newUser);
        return assembler.toModel(newUser);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        User user = assembler.toEntity(dto);
        user.setId(id);
        repository.save(user);
        return assembler.toModel(user);
    }

    @Transactional
    public User buscarOuFalhar(Long userId) {
        return repository.findById(userId).orElseThrow(() -> new UserNaoEncontradoException(userId));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNaoEncontradoException(id);
        }
        repository.deleteById(id);
    }
}
