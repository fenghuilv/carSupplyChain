package com.dongtech.controller;


import com.dongtech.service.CarVGoodsService;
import com.dongtech.vo.CarGoods;
import com.dongtech.vo.EchartsData;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("cargoods")
public class RestController {

    @Resource
    private CarVGoodsService carVGoodsService;

    //获取柱状图数据
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

    // 获取饼图数据
    @RequestMapping("/pieData")
    public EchartsData pieData(){
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

        long sum = list.stream().mapToLong(CarGoods::getNum).sum();
        for (CarGoods c:list) {
            xData.add(c.getName());
            //   BigDecimal percent = BigDecimal.valueOf(0);
            Double per = new Double("0");
            DecimalFormat df1 = new DecimalFormat("#.00");
            //     percent.add(BigDecimal.valueOf(c.getNum()/(sum > Long.valueOf(0) ? sum:1)));
            per = Double.valueOf(c.getNum()/(sum > Long.valueOf(0) ? sum:1));
            per = Double.valueOf(df1.format(per));
            serieData.add(BigDecimal.valueOf(per));
        }
        //   long sum = serieData.stream().reduce(Integer::sum).orElse(0);

        echartsData.setTitle("饼图实例");
        echartsData.setLegendData(Stream.of("商品").collect(Collectors.toList()));
        echartsData.setName("价格（接口数据）");
        echartsData.setxData(xData);
        echartsData.setSerieData(serieData);
        return echartsData;
    }
}
