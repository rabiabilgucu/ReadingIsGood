package com.getir.ReadingIsGood.controller;

import com.getir.ReadingIsGood.dto.response.StatisticsResponseDto;
import com.getir.ReadingIsGood.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;


    @GetMapping("/allStatistics/{id}")
    public ResponseEntity<List<StatisticsResponseDto>> allStatistics(@PathVariable long id){
        List<StatisticsResponseDto> statisticsResponseDtoList = statisticsService.allStatistics(id);
        return new ResponseEntity<>(statisticsResponseDtoList, HttpStatus.OK);
    }

}
