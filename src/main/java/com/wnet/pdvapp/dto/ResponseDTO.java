package com.wnet.pdvapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class ResponseDTO<T> {

    @Getter
    private List<String> messages;
    @Getter
    private T data;

    public ResponseDTO(List<String> messages, T data){
        this.messages = messages;
        this.data = data;
    }

    public ResponseDTO(String messages, T data){
        this.messages = Arrays.asList(messages);
        this.data = data;
    }
}
