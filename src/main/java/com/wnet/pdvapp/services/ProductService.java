package com.wnet.pdvapp.services;

import com.wnet.pdvapp.assembler.ProductAssembler;
import com.wnet.pdvapp.dto.ProductDTO;
import com.wnet.pdvapp.entity.Product;
import com.wnet.pdvapp.entity.exceptions.ProdutoNaoEncontradoException;
import com.wnet.pdvapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductAssembler assembler;

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll(){
        return assembler.toCollectionModel(repository.findAll());
    }

    @Transactional
    public ProductDTO salvar(ProductDTO dto) {
        Product newProduct = assembler.toEntity(dto);
        repository.save(newProduct);
        return assembler.toModel(newProduct);
    }
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        Product product = assembler.toEntity(dto);
        product.setId(id);
        repository.save(product);
        return assembler.toModel(product);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ProdutoNaoEncontradoException(id);
        }
        repository.deleteById(id);
    }
}
