package com.dongtech.service.impl;


import com.dongtech.dao.CarGoodsDao;
import com.dongtech.dao.impl.CarGoodsDaoImpl;
import com.dongtech.service.CarVGoodsService;
import com.dongtech.vo.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CarGoodsServiceImpl implements CarVGoodsService {

    CarGoodsDao dao = new CarGoodsDaoImpl();


    @Override
    public List<CarGoods> queryList(CarGoods carGoods) throws SQLException {
        return dao.queryList(carGoods);
    }

    @Override
    public List<CarOrders> queryOrders() {
        return dao.queryOrders();
    }

    @Override
    public List<CarOrderDetails> queryOrdersDetails(Integer id) {
        return dao.queryOrdersDetails(id);
    }

    @Override
    public List<ProduceOrderDetails> getProduceOrderDetails(Integer id) {
         List<CarOrderDetails> list = this.queryOrdersDetails(id);

        // 根据明细进行生产订单分拆，判断存在则更新，不存在则拆单插入
        for(CarOrderDetails c:list){
            ProduceOrderDetails tdd = dao.queryOrdersTearDownDetails(c);
            if(tdd !=null){
                tdd.setTot_num(c.getNum());
                dao.updateTearDownDetails(tdd);
            }else {
                ProduceOrderDetails t = new ProduceOrderDetails();
                t.setTot_num(c.getNum());
                t.setGoods_name(c.getGoodsname());
                t.setOrder_id(c.getOrderId());
                t.setProduce(c.getProduce());
                t.setTot_price(c.getPrice());
                dao.saveTearDownDetails(t);
            }
        }
        List<ProduceOrderDetails>  listProduceOrderDetails = dao.queryOrdersTearDownDetailsByID(id);
        return listProduceOrderDetails;
    }

    @Override
    public void saveOrders(List<Cart> carIncookie) {
        dao.saveOrders(carIncookie);
    }

    @Override
    public ProduceOrderDetails queryOrdersTearDownDetails(CarOrderDetails c) {
        return dao.queryOrdersTearDownDetails(c);
    }

    @Override
    public void updateTearDownDetails(ProduceOrderDetails tdd) {
        dao.updateTearDownDetails(tdd);
    }

    @Override
    public void saveTearDownDetails(ProduceOrderDetails tdd) {
        dao.saveTearDownDetails(tdd);
    }
}
