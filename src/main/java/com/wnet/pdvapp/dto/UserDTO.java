package com.wnet.pdvapp.dto;

import com.wnet.pdvapp.entity.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private boolean isEnable;
    private List<Sale> sales;
}
