package com.dongtech.vo;

import java.math.BigDecimal;
import java.util.List;

public class EchartsData {
        /**
         * 标题
         */
        private String title;

    /**
     * 标题
     */
    private String name;

        /**
         * 顶部数据
         */
        private List<String> legendData;

        /**
         * 横坐标数据
         */
        private List<String> xData;

        /**
         * 柱状图上的具体数据
         */
        private List<BigDecimal> serieData;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLegendData() {
        return legendData;
    }

    public void setLegendData(List<String> legendData) {
        this.legendData = legendData;
    }

    public List<String> getxData() {
        return xData;
    }

    public void setxData(List<String> xData) {
        this.xData = xData;
    }

    public List<BigDecimal> getSerieData() {
        return serieData;
    }

    public void setSerieData(List<BigDecimal> serieData) {
        this.serieData = serieData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
