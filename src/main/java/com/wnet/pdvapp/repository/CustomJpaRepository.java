package com.wnet.pdvapp.repository;

import com.wnet.pdvapp.entity.exceptions.EntidadeNaoEncontradaException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {
    Optional<T> buscarPrimeiro();
    T buscarOuFalhar(ID id) throws EntidadeNaoEncontradaException;

}
