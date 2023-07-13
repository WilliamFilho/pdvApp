package com.wnet.pdvapp.entity.exceptions;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) //, reason = "Entidade não encontrada")
public class UserNaoEncontradoException extends EntidadeNaoEncontradaException {

    public UserNaoEncontradoException(String mensagem) {
        super( mensagem);
    }

    //public EntidadeNaoEncontradaException(HttpStatusCode status, String mensagem) {
        //super(status, mensagem);
    //}

    public UserNaoEncontradoException(Long userId){
        this(String.format("Não existe um cadastro de usuário com código %d", userId));
    }
}