package com.ssafy.lirent.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.lirent.model.dto.BasketDto;
import com.ssafy.lirent.model.mapper.BasketMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketMapper basketMapper;

    @Transactional
    public void insertBasket(BasketDto basket) {
        basketMapper.insertBasket(basket);
    }

    @Transactional
    public void deleteBasket(int memberId, int subleaseId) {
        basketMapper.deleteBasket(memberId, subleaseId);
    }

    public List<BasketDto> getAllBaskets(int memberId) {
        return basketMapper.selectAllBaskets(memberId);
    }
}
