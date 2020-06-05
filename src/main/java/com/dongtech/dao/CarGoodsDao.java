package com.dongtech.dao;


import com.dongtech.vo.*;

import java.util.List;

public interface CarGoodsDao {
    List<CarGoods> queryList(CarGoods carGoods);

    List<CarOrders> queryOrders();

    List<CarOrderDetails> queryOrdersDetails(Integer id);

    void saveOrders(List<Cart> carIncookie);

    ProduceOrderDetails queryOrdersTearDownDetails(CarOrderDetails c);

    void updateTearDownDetails(ProduceOrderDetails tdd);

    void saveTearDownDetails(ProduceOrderDetails tdd);

    List<ProduceOrderDetails> queryOrdersTearDownDetailsByID(Integer id);
}
