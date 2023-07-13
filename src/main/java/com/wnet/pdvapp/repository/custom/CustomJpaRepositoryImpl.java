package com.wnet.pdvapp.repository.custom;

import com.wnet.pdvapp.entity.exceptions.EntidadeNaoEncontradaException;
import com.wnet.pdvapp.entity.exceptions.NegocioException;
import com.wnet.pdvapp.repository.CustomJpaRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.Optional;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {
    private EntityManager manager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.manager = entityManager;
    }

    @Override
    public Optional<T> buscarPrimeiro() {
        var jpql = "from " + getDomainClass().getName();
        T entity = manager.createQuery(jpql, getDomainClass()).setMaxResults(1).getSingleResult();
        return Optional.ofNullable(entity);
    }

    @Override
    public T buscarOuFalhar(ID id) throws EntidadeNaoEncontradaException {
            return findById(id)
                    .orElseThrow(() -> new NegocioException(
                            String.format("Não existe um cadastro de %s com código %s",
                                    getDomainClass().getCanonicalName(), id)));
        }
}
