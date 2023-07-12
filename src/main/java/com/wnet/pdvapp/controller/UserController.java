package com.wnet.pdvapp.controller;

import com.wnet.pdvapp.dto.UserDTO;
import com.wnet.pdvapp.entity.User;
import com.wnet.pdvapp.entity.exceptions.EntidadeNaoEncontradaException;
import com.wnet.pdvapp.entity.exceptions.UserNaoEncontradoException;
import com.wnet.pdvapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity getAll(){
        return new ResponseEntity(service.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> adicionar(@RequestBody UserDTO dto) {
        try{
            return new ResponseEntity<UserDTO>(service.salvar(dto), HttpStatus.CREATED);
        }catch (EntidadeNaoEncontradaException e){
            throw new UserNaoEncontradoException(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> atualizar(@PathVariable Long id, @RequestBody UserDTO dto){
        User user = service.buscarOuFalhar(id);
        return ResponseEntity.ok((service.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id){
        service.delete(id);
    }
}

