package com.getir.ReadingIsGood.service.impl;

import com.getir.ReadingIsGood.dto.response.StatisticsResponseDto;
import com.getir.ReadingIsGood.entity.OrderEntity;
import com.getir.ReadingIsGood.repository.BookRepository;
import com.getir.ReadingIsGood.repository.OrderRepository;
import com.getir.ReadingIsGood.service.StatisticsService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final OrderRepository orderRepository;

    private final BookRepository bookRepository;

    private static final Logger logger =
            (Logger) LoggerFactory.getLogger(StatisticsServiceImpl.class);

    @Override
    @Transactional
    public List<StatisticsResponseDto> allStatistics(long id) {
        logger.debug("***************************************");
        logger.debug("Customer ID: " + id);
        List<OrderEntity> orders = orderRepository.findAllByCustomerID(id);

        Map<Month, List<OrderEntity>> orderMap = orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCreatedDate().getMonth()));

        List<StatisticsResponseDto> statisticsResponseDtoList = new ArrayList<>();

        for (Map.Entry<Month, List<OrderEntity>> entry : orderMap.entrySet()) {
            int totalBook = 0;
            int totalOrder = 0;
            float totalPrice = 0;
            String month = "";
            for (OrderEntity orderEntity : entry.getValue()) {
                logger.debug("Order entity: " + orderEntity);
                month = String.valueOf(orderEntity.getCreatedDate().getMonth());
                totalBook += orderEntity.getOrderAmount();
                totalOrder += 1;
                totalPrice += priceOfBook(orderEntity);
            }
            logger.debug("***************************************");
            logger.debug("Total price: " + totalPrice);
            logger.debug("Total totalOrder: " + totalOrder);
            logger.debug("Total totalBook: " + totalBook);
            logger.debug("***************************************");
            statisticsResponseDtoList.add(StatisticsResponseDto.builder()
                    .totalPrice(totalPrice)
                    .totalOrder(totalOrder)
                    .month(month)
                    .totalBook(totalBook)
                    .build());
        }

        return statisticsResponseDtoList;
    }

    private float priceOfBook(OrderEntity orderEntity){
        return bookRepository.getByBookID(orderEntity.getBookID()).getPrice();
    }
}
