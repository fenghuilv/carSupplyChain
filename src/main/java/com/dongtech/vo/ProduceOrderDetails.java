package com.dongtech.vo;

import java.math.BigDecimal;

/**
 * @author gzl
 * @date 2020-04-15
 * @program: springboot-jsp
 * @description: ${description}
 */
public class ProduceOrderDetails {
    private Long id;
    private Integer order_id;
    private String produce;
    private String goods_name;
    private Integer tot_num;
    private BigDecimal tot_price;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Integer getTot_num() {
        return tot_num;
    }

    public void setTot_num(Integer tot_num) {
        this.tot_num = tot_num;
    }

    public BigDecimal getTot_price() {
        return tot_price;
    }

    public void setTot_price(BigDecimal tot_price) {
        this.tot_price = tot_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getProduce() {
        return produce;
    }

    public void setProduce(String produce) {
        this.produce = produce;
    }


    public ProduceOrderDetails() {
    }

    public ProduceOrderDetails(Long id, Integer order_id, String produce, String goods_name, Integer tot_num, BigDecimal tot_price) {
        this.id = id;
        this.order_id = order_id;
        this.produce = produce;
        this.goods_name = goods_name;
        this.tot_num = tot_num;
        this.tot_price = tot_price;
    }
}
