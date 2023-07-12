package com.wnet.pdvapp.entity.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) //, reason = "Entidade não encontrada")
public abstract class EntidadeNaoEncontradaException extends NegocioException {

    public EntidadeNaoEncontradaException(String mensagem) {
        super( mensagem);
    }

    //public EntidadeNaoEncontradaException(HttpStatusCode status, String mensagem) {
        //super(status, mensagem);
    //}
}