package com.getir.ReadingIsGood.service;

import com.getir.ReadingIsGood.dto.response.StatisticsResponseDto;

import java.util.List;

public interface StatisticsService {
    List<StatisticsResponseDto> allStatistics(long id);
}
