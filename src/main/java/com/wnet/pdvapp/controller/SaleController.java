package com.wnet.pdvapp.controller;

import com.wnet.pdvapp.dto.ResponseDTO;
import com.wnet.pdvapp.dto.SaleDTO;
import com.wnet.pdvapp.entity.exceptions.InvalidOperationException;
import com.wnet.pdvapp.entity.exceptions.NoItemException;
import com.wnet.pdvapp.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(new ResponseDTO<>("", saleService.findById(id)), HttpStatus.OK);
        } catch (NoItemException | InvalidOperationException error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    private ResponseEntity findAll(){
        return new ResponseEntity<>(new ResponseDTO<>("", saleService.findAll()), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity adicionar(@RequestBody SaleDTO dto){
        try {
            long id = saleService.save(dto);
            return new ResponseEntity<>(new ResponseDTO<>("Venda realizada com sucesso: ", id), HttpStatus.CREATED);
        }catch (NoItemException | InvalidOperationException error){
            return new ResponseEntity<>(new ResponseDTO<>(error.getMessage(), null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseDTO<>(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
