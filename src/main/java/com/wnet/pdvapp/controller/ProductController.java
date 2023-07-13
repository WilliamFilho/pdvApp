package com.wnet.pdvapp.controller;

import com.wnet.pdvapp.dto.ProductDTO;
import com.wnet.pdvapp.entity.Product;
import com.wnet.pdvapp.entity.exceptions.EntidadeNaoEncontradaException;
import com.wnet.pdvapp.entity.exceptions.ProdutoNaoEncontradoException;
import com.wnet.pdvapp.repository.ProductRepository;
import com.wnet.pdvapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public List<ProductDTO> findAll(){
        return ResponseEntity.ok(service.findAll()).getBody();
    }

    @PostMapping
    public ResponseEntity<ProductDTO> adicionar(@RequestBody ProductDTO dto) {
        try{
            return new ResponseEntity<ProductDTO>(service.salvar(dto), HttpStatus.CREATED);
        }catch (EntidadeNaoEncontradaException e){
            throw new ProdutoNaoEncontradoException(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> atualizar(@PathVariable Long id, @RequestBody ProductDTO dto){
        Product product = repository.buscarOuFalhar(id);
        return ResponseEntity.ok((service.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id){
        service.delete(id);
    }

}
