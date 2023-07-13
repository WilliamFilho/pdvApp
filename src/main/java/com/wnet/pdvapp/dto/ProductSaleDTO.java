package com.wnet.pdvapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaleDTO {

    @NotNull(message = "O item da venda é obrigatório")
    private long productid;
    @NotNull(message = "O campo quantidade é obrigatório")
    private int quantity;
}
