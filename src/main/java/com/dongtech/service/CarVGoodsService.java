package com.dongtech.service;

import com.dongtech.vo.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface CarVGoodsService {

    List<CarGoods> queryList(CarGoods carGoods) throws SQLException;

    List<CarOrders> queryOrders();

    List<CarOrderDetails> queryOrdersDetails(Integer id);

    List<ProduceOrderDetails> getProduceOrderDetails(Integer id);

    void saveOrders(List<Cart> carIncookie);

    ProduceOrderDetails queryOrdersTearDownDetails(CarOrderDetails c);

    void updateTearDownDetails(ProduceOrderDetails tdd);

    void saveTearDownDetails(ProduceOrderDetails t);
}
