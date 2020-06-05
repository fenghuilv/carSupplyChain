package com.dongtech.dao.impl;


import com.dongtech.dao.CarGoodsDao;
import com.dongtech.util.JDBCUtil;
import com.dongtech.vo.*;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据层，只负责与数据库的数据交互，将数据进行存储读取操作
 */
public class CarGoodsDaoImpl implements CarGoodsDao {


    @Override
    public List<CarGoods> queryList(CarGoods carGoods) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CarGoods> bookList = new ArrayList<CarGoods>();
        try {
            //1 加载数据库驱动  2 获取数据库连接
           // conn = JDBCUtil.getMysqlConn();
            conn =  JDBCUtil.getMysqlConn();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM cargoods where 1=1");
            if (!StringUtils.isEmpty(carGoods.getId())) {
                sql.append(" and id =").append(carGoods.getId());
            }
            if (!StringUtils.isEmpty(carGoods.getName())) {
                sql.append("  and name like '%").append(carGoods.getName()).append("%'");
            }
            if (!StringUtils.isEmpty(carGoods.getType())) {
                sql.append("  and type='").append(carGoods.getType()).append("'");
            }
            //3 操作数据库——查询一条数据记录
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //4 处理返回数据——将返回的一条记录封装到一个JavaBean对象
            while (rs.next()) {
                CarGoods vo = new CarGoods(rs.getLong("id"),
                        rs.getString("number"),
                        rs.getString("name"),
                        rs.getString("produce"),
                        rs.getBigDecimal("price"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getInt("num")

                );
                bookList.add(vo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5 关闭连接
            JDBCUtil.close(rs, ps, conn);
        }
        return bookList;
    }

    /**
     * @Author gzl
     * @Description：查询订单信息
     * @Exception
     * @Date： 2020/4/20 12:04 AM
     */
    @Override
    public List<CarOrders> queryOrders() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CarOrders> carOrdersList = new ArrayList<CarOrders>();
        try {
            //1 加载数据库驱动  2 获取数据库连接
            conn = JDBCUtil.getMysqlConn();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM car_orders where 1=1");
            //3 操作数据库——查询一条数据记录
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //4 处理返回数据——将返回的一条记录封装到一个JavaBean对象
            while (rs.next()) {
                CarOrders vo = new CarOrders(rs.getLong("id"),
                        rs.getString("number"),
                        rs.getBigDecimal("price")

                );
                carOrdersList.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5 关闭连接
            JDBCUtil.close(rs, ps, conn);
        }
        return carOrdersList;
    }

    /**
     * @Author gzl
     * @Description：查询订单详情
     * @Exception
     * @Date： 2020/4/20 12:17 AM
     */
    @Override
    public List<CarOrderDetails> queryOrdersDetails(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CarOrderDetails> carOrderDetailsList = new ArrayList<CarOrderDetails>();
        try {
            //1 加载数据库驱动  2 获取数据库连接
            conn = JDBCUtil.getMysqlConn();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM car_orders_details where 1=1");
            if (!StringUtils.isEmpty(id)) {
                sql.append(" and order_id =").append(id);
            }
            //3 操作数据库——查询一条数据记录
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //4 处理返回数据——将返回的一条记录封装到一个JavaBean对象
            while (rs.next()) {
                CarOrderDetails vo = new CarOrderDetails(rs.getLong("id"),
                        rs.getString("goods_name"),
                        rs.getInt("num"),
                        rs.getString("produce"),
                        rs.getBigDecimal("price"),
                        rs.getInt("order_id")

                );
                carOrderDetailsList.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5 关闭连接
            JDBCUtil.close(rs, ps, conn);
        }
        return carOrderDetailsList;
    }

    @Override
    public void saveOrders(List<Cart> carIncookie) {
        long randomNum = System.currentTimeMillis() / 1000;
        saveOrdersDetailsLists(carIncookie, randomNum);
        saveOrdersLists(carIncookie, randomNum);
    }

    @Override
    public ProduceOrderDetails queryOrdersTearDownDetails(CarOrderDetails c) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProduceOrderDetails produceOrderDetails = null;
        try {
            //1 加载数据库驱动  2 获取数据库连接
            conn = JDBCUtil.getMysqlConn();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM car_produceOrder_details where 1=1");
            if (!StringUtils.isEmpty(c.getOrderId())) {
                sql.append(" and order_id =").append(c.getOrderId());
            }

            if (!StringUtils.isEmpty(c.getGoodsname())) {
                sql.append(" and goods_name ='").append(c.getGoodsname()).append("'");
            }

            if (!StringUtils.isEmpty(c.getProduce())) {
                sql.append(" and produce ='").append(c.getProduce()).append("'");
            }
            //3 操作数据库——查询一条数据记录
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //4 处理返回数据——将返回的一条记录封装到一个JavaBean对象
            while (rs.next()) {
                produceOrderDetails = new ProduceOrderDetails(rs.getLong("id"),
                        rs.getInt("order_Id"),
                        rs.getString("produce"),
                        rs.getString("goods_name"),
                        rs.getInt("tot_num"),
                        rs.getBigDecimal("tot_price")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5 关闭连接
            JDBCUtil.close(rs, ps, conn);
        }
        return produceOrderDetails;
    }

    @Override
    public void updateTearDownDetails(ProduceOrderDetails tdd) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1 加载数据库驱动  2 获取数据库连接
            conn = JDBCUtil.getMysqlConn();
            String sql = "UPDATE jk_pro_db.car_produceOrder_details set tot_num = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, tdd.getTot_num());
            ps.setLong(2, tdd.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5 关闭连接
            JDBCUtil.close(rs, ps, conn);
        }
    }

    @Override
    public void saveTearDownDetails(ProduceOrderDetails tdd) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1 加载数据库驱动  2 获取数据库连接
            conn = JDBCUtil.getMysqlConn();
            String sql = "INSERT INTO jk_pro_db.car_produceOrder_details(id,produce,goods_name, tot_num,tot_price,order_id) values (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,(Long) System.currentTimeMillis() / 1000 );
            ps.setString(2, tdd.getProduce());
            ps.setString(3, tdd.getGoods_name());
            ps.setInt(4, tdd.getTot_num());
            ps.setBigDecimal(5, tdd.getTot_price());
            ps.setInt(6, tdd.getOrder_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5 关闭连接
            JDBCUtil.close(rs, ps, conn);
        }
    }

    @Override
    public List<ProduceOrderDetails> queryOrdersTearDownDetailsByID(Integer id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProduceOrderDetails produceOrderDetails = null;
        List<ProduceOrderDetails> produceOrderDetailsList = new ArrayList<>();
        try {
            //1 加载数据库驱动  2 获取数据库连接
            conn = JDBCUtil.getMysqlConn();
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT * FROM car_produceOrder_details where 1=1");
            if (!StringUtils.isEmpty(id)) {
                sql.append(" and order_id =").append(id);
            }

            //3 操作数据库——查询一条数据记录
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            //4 处理返回数据——将返回的一条记录封装到一个JavaBean对象
            while (rs.next()) {
                produceOrderDetails = new ProduceOrderDetails(rs.getLong("id"),
                        rs.getInt("order_Id"),
                        rs.getString("produce"),
                        rs.getString("goods_name"),
                        rs.getInt("tot_num"),
                        rs.getBigDecimal("tot_price")
                );
                produceOrderDetailsList.add(produceOrderDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5 关闭连接
            JDBCUtil.close(rs, ps, conn);
        }
        return produceOrderDetailsList;

    }

    private void saveOrdersLists(List<Cart> carIncookieLists, long randomNum) {
        int price = 0;
        for (Cart carIncookie : carIncookieLists) {
            price += carIncookie.getPrice() * carIncookie.getNum();
        }
        saveOrder(price, randomNum);
    }

    private void saveOrdersDetailsLists(List<Cart> carIncookieLists, long randomNum) {
        for (Cart carIncookie : carIncookieLists) {
            saveOrdersDetails(carIncookie.getName(), carIncookie.getNum(), carIncookie.getProduce(), carIncookie.getPrice(), (int) randomNum);
        }
    }


    public void saveOrdersDetails(String goods_name, int num, String produce, int price, int order_id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1 加载数据库驱动  2 获取数据库连接
            conn = JDBCUtil.getMysqlConn();

            final int[] totalprice = {0};
            String sql = "INSERT INTO jk_pro_db.car_orders_details(goods_name, num,produce,price,order_id,id) values (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            long randomNum = System.currentTimeMillis();
            ps.setString(1, goods_name);
            ps.setInt(2, num);
            ps.setString(3, produce);
            ps.setInt(4, price);
            ps.setInt(5, order_id);
            ps.setInt(6, (int) randomNum);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5 关闭连接
            JDBCUtil.close(rs, ps, conn);
        }
    }


    public void saveOrder(int price, long randomNum) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1 加载数据库驱动  2 获取数据库连接
            conn = JDBCUtil.getMysqlConn();
            final int[] totalprice = {0};
            String sql = "INSERT INTO jk_pro_db.car_orders(number, price,id) values (?,?,?)";
            ps = conn.prepareStatement(sql);
//            long randomNum = System.currentTimeMillis();
            ps.setString(1, String.valueOf(randomNum));
            ps.setBigDecimal(2, BigDecimal.valueOf(price));
            ps.setBigDecimal(3, BigDecimal.valueOf(randomNum));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5 关闭连接
            JDBCUtil.close(rs, ps, conn);
        }
    }

}
