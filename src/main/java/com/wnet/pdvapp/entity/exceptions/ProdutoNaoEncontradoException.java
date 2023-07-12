package com.wnet.pdvapp.entity.exceptions;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) //, reason = "Entidade não encontrada")
public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ProdutoNaoEncontradoException(String mensagem) {
        super( mensagem);
    }

    //public EntidadeNaoEncontradaException(HttpStatusCode status, String mensagem) {
        //super(status, mensagem);
    //}

    public ProdutoNaoEncontradoException(Long produtoId){
        this(String.format("Não existe um cadastro de produto com código %d", produtoId));
    }
}