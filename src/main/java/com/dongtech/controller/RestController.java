package com.dongtech.controller;


import com.dongtech.service.CarVGoodsService;
import com.dongtech.vo.CarGoods;
import com.dongtech.vo.EchartsData;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("cargoods")
public class RestController {

    @Resource
    private CarVGoodsService carVGoodsService;

    @RequestMapping("/barData")
    public EchartsData barData(){
        EchartsData echartsData = new EchartsData();
        List<CarGoods> list = new ArrayList<>();
        try {
            CarGoods carGoods = new CarGoods();
            list = carVGoodsService.queryList(carGoods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<String> xData = new ArrayList<>();
        List<BigDecimal> serieData = new ArrayList<>();

        for (CarGoods c:list) {
            xData.add(c.getName());
            serieData.add(c.getPrice());
        }

        echartsData.setTitle("柱状图实例");
        echartsData.setLegendData(Stream.of("商品").collect(Collectors.toList()));
        echartsData.setName("数量");
        echartsData.setxData(xData);
        echartsData.setSerieData(serieData);

        return echartsData;
    }

}
