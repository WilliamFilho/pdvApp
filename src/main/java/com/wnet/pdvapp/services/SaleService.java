package com.wnet.pdvapp.services;

import com.wnet.pdvapp.assembler.SaleInfoAssembler;
import com.wnet.pdvapp.dto.*;
import com.wnet.pdvapp.entity.ItemSale;
import com.wnet.pdvapp.entity.Product;
import com.wnet.pdvapp.entity.Sale;
import com.wnet.pdvapp.entity.User;
import com.wnet.pdvapp.entity.exceptions.EntidadeNaoEncontradaException;
import com.wnet.pdvapp.entity.exceptions.InvalidOperationException;
import com.wnet.pdvapp.entity.exceptions.NoItemException;
import com.wnet.pdvapp.entity.exceptions.UserNaoEncontradoException;
import com.wnet.pdvapp.repository.ItemSaleRepository;
import com.wnet.pdvapp.repository.ProductRepository;
import com.wnet.pdvapp.repository.SaleRepository;
import com.wnet.pdvapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ItemSaleRepository itemSaleRepository;
    private final SaleRepository saleRepository;

    private final SaleInfoAssembler saleInfoAssembler;

    @Transactional
    public long save(SaleDTO sale) {
        User user = userRepository.findById(sale.getUserid()).orElseThrow(() -> new UserNaoEncontradoException("Usuário não encontrado!"));

        Sale newSale = new Sale();
        newSale.setUser(user);
        newSale.setDate(LocalDate.now());
        List<ItemSale> items = getItemSale(sale.getItems());

        newSale = saleRepository.save(newSale);
        
        saveItemSale(items, newSale);

        return newSale.getId();

    }

    private void saveItemSale(List<ItemSale> items, Sale newSale) {
        for (ItemSale item: items) {
            item.setSale(newSale);
            itemSaleRepository.save(item);
        }
    }

    private List<ItemSale> getItemSale(List<ProductSaleDTO> products){
        if(products.isEmpty()){
            throw new InvalidOperationException("Não é possível adicionar venda sem itens!");
        }

        return products.stream().map(item -> {
            Product product = productRepository.getReferenceById(item.getProductid());
            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());

            if(product.getQuantity() == 0){
                throw new NoItemException("Produto sem estoque");
            }else if(product.getQuantity() < item.getQuantity()){
                throw  new InvalidOperationException(String.format("A quantidade de itens da venda (%s) é > do que a quantidade disponível no estoque (%s)", item.getQuantity(), product.getQuantity()));
            }

            int total = product.getQuantity() - item.getQuantity();
            product.setQuantity(total);
            productRepository.save(product);

            return itemSale;
        }).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<SaleInfoDTO> findAll(){
        return saleRepository.findAll().stream().map(sale -> getSaleInfo(sale)).collect(Collectors.toList());
    }

    private SaleInfoDTO getSaleInfo(Sale sale) {
        SaleInfoDTO saleInfoDTO = new SaleInfoDTO();
        saleInfoDTO.setUser(sale.getUser().getName());
        saleInfoDTO.setDate(sale.getDate().format(DateTimeFormatter.ISO_DATE));
        saleInfoDTO.setProducts(getProductInfo(sale.getItems()));
        return saleInfoDTO;
    }

    private List<ProductInfoDTO> getProductInfo(List<ItemSale> items) {
        return items.stream().map(itemSale -> {
            ProductInfoDTO productInfoDTO = new ProductInfoDTO();
            productInfoDTO.setDescription(itemSale.getProduct().getDescription());
            productInfoDTO.setQuantity(itemSale.getQuantity());
            return productInfoDTO;
        }).collect(Collectors.toList());
    }

    public SaleInfoDTO findById(Long id) {
        Sale sale = saleRepository.buscarOuFalhar(id);
        return  getSaleInfo(sale);
    }
}
