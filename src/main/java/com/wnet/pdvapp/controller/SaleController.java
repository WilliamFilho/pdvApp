package com.wnet.pdvapp.controller;

import com.wnet.pdvapp.dto.SaleDTO;
import com.wnet.pdvapp.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping
    public ResponseEntity adicionar(@RequestBody SaleDTO dto){
        try{
            long id = saleService.save(dto);
            return  new ResponseEntity<>("Venda realizada com sucesso: "+ id, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
