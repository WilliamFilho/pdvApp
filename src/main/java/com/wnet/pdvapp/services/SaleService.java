package com.wnet.pdvapp.services;

import com.wnet.pdvapp.dto.ProductSaleDTO;
import com.wnet.pdvapp.dto.SaleDTO;
import com.wnet.pdvapp.entity.ItemSale;
import com.wnet.pdvapp.entity.Product;
import com.wnet.pdvapp.entity.Sale;
import com.wnet.pdvapp.entity.User;
import com.wnet.pdvapp.repository.ItemSaleRepository;
import com.wnet.pdvapp.repository.ProductRepository;
import com.wnet.pdvapp.repository.SaleRepository;
import com.wnet.pdvapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ItemSaleRepository itemSaleRepository;
    private final SaleRepository saleRepository;

    @Transactional
    public long save(SaleDTO sale) {
        User user = userRepository.findById(sale.getUserid()).get();

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

    private List<ItemSale> getItemSale(List<ProductSaleDTO> products) {
        return products.stream().map(item -> {
            Product product = productRepository.getReferenceById(item.getProductid());
            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());

            return itemSale;
        }).collect(Collectors.toList());
    }
}
