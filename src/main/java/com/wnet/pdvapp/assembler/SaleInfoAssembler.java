package com.wnet.pdvapp.assembler;

import com.wnet.pdvapp.dto.ProductDTO;
import com.wnet.pdvapp.dto.SaleInfoDTO;
import com.wnet.pdvapp.entity.Product;
import com.wnet.pdvapp.entity.Sale;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class SaleInfoAssembler {
    private ModelMapper modelMapper;

    public SaleInfoDTO toModel(Sale sale) {
        return modelMapper.map(sale, SaleInfoDTO.class);
    }


    //qdo precisar de um List...
    public List<SaleInfoDTO> toCollectionModel(List<Sale> sales) {
        return sales.stream().map(this::toModel).collect(Collectors.toList());
    }

    //qdo precisar de um List paginado...
    public Page<SaleInfoDTO> toCollectionModelPage(Page<Sale> sales) {
        return sales.map(this::toModel);
    }

    //de DTO para Entity
    public Sale toEntity(SaleInfoDTO dto){
        return modelMapper.map(dto, Sale.class);
    }
}
