package com.dongtech.controller;

import com.dongtech.service.CarVGoodsService;
import com.dongtech.vo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gzl
 * @date 2020-04-15
 * @program: springboot-jsp
 * @description: ${description}
 */
@Controller
@RequestMapping("cargoods")
public class CarGoodsController {

    @Resource
    private CarVGoodsService carVGoodsService;


    /**
     * @Author gzl
     * @Description：查询商品列表
     * @Exception
     */
    @RequestMapping("/queryList")
    public ModelAndView queryList(CarGoods carGoods) {
        List<CarGoods> list = new ArrayList<>();
        try {
            list = carVGoodsService.queryList(carGoods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /**
         * 模型和视图
         * model模型: 模型对象中存放了返回给页面的数据
         * view视图: 视图对象中指定了返回的页面的位置
         */
        ModelAndView modelAndView = new ModelAndView();
        //将返回给页面的数据放入模型和视图对象中
        modelAndView.addObject("list", list);
        //指定返回的页面位置
        modelAndView.setViewName("carGoods/list");
        return modelAndView;
    }


    /**
     * @Author gb
     * @Description：查询购物车商品列表
     * @Exception
     */
    @RequestMapping("/getCart")
    public ModelAndView getCartList(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        List<Cart> carIncookie = getCartInCookie(response, request);

        /**
         * 模型和视图
         * model模型: 模型对象中存放了返回给页面的数据
         * view视图: 视图对象中指定了返回的页面的位置
         */
        ModelAndView modelAndView = new ModelAndView();
        //将返回给页面的数据放入模型和视图对象中
        modelAndView.addObject("list", carIncookie);
        //指定返回的页面位置
        modelAndView.setViewName("carGoods/carlist");
        return modelAndView;
    }

    @RequestMapping("/clearcar")
    public ModelAndView clearcar(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //下单之后清除购物车cookie
        Cookie cookie = getCookie(request);
        if(cookie !=null) {
            cookie.setMaxAge(0);//设置cookie有效时间为0
            cookie.setPath("/"); //不设置存储路径
            response.addCookie(cookie);
        }


        ModelAndView modelAndView = new ModelAndView();
        //将返回给页面的数据放入模型和视图对象中
        List<CarGoods> list = new ArrayList<>();

        modelAndView.addObject("list", list);
        //指定返回的页面位置
        modelAndView.setViewName("carGoods/list");

        return modelAndView;
    }


        /**
         * @Author gb
         * @Description：下单购物车商品列表
         * @Exception
         */
    @RequestMapping("/addorders")
    public ModelAndView addorders(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        List<Cart> carIncookie = getCartInCookie(response, request);
        if(carIncookie != null && carIncookie.size()>0) {
            carVGoodsService.saveOrders(carIncookie);
        }

        //下单之后清除购物车cookie
        Cookie cookie = getCookie(request);
        if(cookie !=null) {
            cookie.setMaxAge(0);//设置cookie有效时间为0
            cookie.setPath("/"); //不设置存储路径
        }
        response.addCookie(cookie);

        /**
         * 模型和视图
         * model模型: 模型对象中存放了返回给页面的数据
         * view视图: 视图对象中指定了返回的页面的位置
         */
        ModelAndView modelAndView = new ModelAndView();
        //将返回给页面的数据放入模型和视图对象中
        List<CarGoods> list = new ArrayList<>();
        try {
            CarGoods carGoods = new CarGoods();
            list = carVGoodsService.queryList(carGoods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("list", list);
        //指定返回的页面位置
        modelAndView.setViewName("carGoods/list");
        CarGoods carGoods = new CarGoods();
        return queryList(carGoods);
    }


    /**
     * @Author gb
     * @Description：添加购物车商品列表
     * @Exception
     */
    @RequestMapping("/addGoodsToCart")
    @ResponseBody
    public ModelAndView addGoodsToCart(Integer goodsId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        List<Cart> cartVos = getCartInCookie(response, request);
        Cookie cookie_2st;
        CarGoods carGoods = new CarGoods();

        try {
            CarGoods carGoods1 = new CarGoods();
            carGoods1.setId(Long.parseLong(goodsId + ""));
            List<CarGoods> cList = carVGoodsService.queryList(carGoods1);
            carGoods = cList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //如果购物车列表为空
        if (cartVos.size() <= 0) {
            Cart cartVo = new Cart();
            cartVo.setNum(1);
            cartVo.setPrice(carGoods.getPrice().intValue());
            cartVo.setId(carGoods.getId());
            cartVo.setType(carGoods.getType());
            cartVo.setName(carGoods.getName());
            cartVo.setProduce(carGoods.getProduce());
            cartVo.setDescription(carGoods.getDescription());
            cartVo.setNumber(carGoods.getNumber());

            //将当前传来的商品添加到列表
            cartVos.add(cartVo);
            if (getCookie(request) == null) {
                cookie_2st = new Cookie("cart", URLEncoder.encode(makeCookieValue(cartVos)));
                cookie_2st.setPath("/");
                cookie_2st.setMaxAge(60 * 30);
                response.addCookie(cookie_2st);
            } else {
                cookie_2st = getCookie(request);
                cookie_2st.setPath("/");
                cookie_2st.setMaxAge(60 * 30);
                cookie_2st.setValue(URLEncoder.encode(makeCookieValue(cartVos)));
            }
        } else {
            int bj = 0;
            for (Cart cart : cartVos) {
                if (cart.getId().equals(goodsId.longValue())) {
                    cart.setNum(cart.getNum() + 1);
                    bj = 1;
                    break;
                }
            }
            if (bj == 0) {
                Cart cartVo = new Cart();
                cartVo.setNum(1);
                cartVo.setPrice(carGoods.getPrice().intValue());
                cartVo.setId(carGoods.getId());
                cartVo.setType(carGoods.getType());
                cartVo.setName(carGoods.getName());
                cartVo.setProduce(carGoods.getProduce());
                cartVo.setDescription(carGoods.getDescription());
                cartVo.setNumber(carGoods.getNumber());
                cartVos.add(cartVo);
            }

            cookie_2st = getCookie(request);
            cookie_2st.setPath("/");
            cookie_2st.setMaxAge(60 * 30);
            cookie_2st.setValue(URLEncoder.encode(makeCookieValue(cartVos)));
            response.addCookie(cookie_2st);
        }
       // return cartVos.toString();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("carGoods/list");
        CarGoods cg = new CarGoods();
        List<CarGoods> list = queryGoods(cg);
        modelAndView.addObject("list", list);
        modelAndView.setViewName("carGoods/list");
        return modelAndView;
    }


    /**
     * @Author gzl
     * @Description：查询下单列表
     * @Exception
     * @Date： 2020/4/19 11:59 PM
     */
    @RequestMapping("/queryorders")
    public ModelAndView QueryOrders() {
        List<CarOrders> list = carVGoodsService.queryOrders();
        /**
         * 模型和视图
         * model模型: 模型对象中存放了返回给页面的数据
         * view视图: 视图对象中指定了返回的页面的位置
         */
        ModelAndView modelAndView = new ModelAndView();
        //将返回给页面的数据放入模型和视图对象中
        modelAndView.addObject("list", list);
        //指定返回的页面位置
        modelAndView.setViewName("carGoods/orderlist");
        return modelAndView;
    }

    /**
     * @Author gzl
     * @Description：查询下单详情列表
     * @Exception
     * @Date： 2020/4/19 11:59 PM
     */
    @RequestMapping("/queryordersdetails")
    public ModelAndView QueryOrdersDetails(Integer id) {
        List<CarOrderDetails> list = carVGoodsService.queryOrdersDetails(id);
        /**
         * 模型和视图
         * model模型: 模型对象中存放了返回给页面的数据
         * view视图: 视图对象中指定了返回的页面的位置
         */
        ModelAndView modelAndView = new ModelAndView();
        //将返回给页面的数据放入模型和视图对象中
        modelAndView.addObject("list", list);
        //指定返回的页面位置
        modelAndView.setViewName("carGoods/orderdetailslist");
        return modelAndView;
    }

    /**
     * @Author lfh
     * @Description：拆单
     * @Exception
     * @Date： 2020/6/6
     */
    @RequestMapping("/teardowndetails")
    public ModelAndView tearDownDetails(@RequestParam Integer orderId) {

        List<ProduceOrderDetails> list = carVGoodsService.getProduceOrderDetails(orderId);

        /**
         * 模型和视图
         * model模型: 模型对象中存放了返回给页面的数据
         * view视图: 视图对象中指定了返回的页面的位置
         */
        ModelAndView modelAndView = new ModelAndView();
        //将返回给页面的数据放入模型和视图对象中
        modelAndView.addObject("list", list);
        //指定返回的页面位置
        modelAndView.setViewName("carGoods/produceorderdetail");
        return modelAndView;
    }

    /**
     * @Author gb
     * @Description：拆单
     * @Exception
     * @Date： 2020/4/19 11:59 PM
     */
    @RequestMapping("/returnView")
    public ModelAndView returnView(@RequestParam String viewname) {

         if("".equals(viewname)){viewname="list";}
        ModelAndView modelAndView = new ModelAndView();


        if("list".equals(viewname)){
            CarGoods cg = new CarGoods();
           List<CarGoods> list = queryGoods(cg);
            modelAndView.addObject("list", list);
            modelAndView.setViewName("carGoods/list");
            return modelAndView;
        }

        if("orderlist".equals(viewname)){
            List<CarOrders> list = queryAllOrders();
            modelAndView.addObject("list", list);
            modelAndView.setViewName("carGoods/orderlist");
            return modelAndView;
        }


        //指定返回的页面位置
       return null;

    }


    /**
     * 获取cookie中的购物车列表
     *
     * @param response
     * @param request
     * @return 购物车列表
     * @throws UnsupportedEncodingException 抛出异常
     */
    public List<Cart> getCartInCookie(HttpServletResponse response, HttpServletRequest request) throws
            UnsupportedEncodingException {
        // 定义空的购物车列表
        List<Cart> items = new ArrayList<>();
        String value_1st;
        // 购物cookie
        Cookie cart_cookie = getCookie(request);
        // 判断cookie是否为空
        if (cart_cookie != null) {
            // 获取cookie中String类型的value,从cookie获取购物车
            value_1st = URLDecoder.decode(cart_cookie.getValue(), "utf-8");
            // 判断value是否为空或者""字符串
            if (value_1st != null && !"".equals(value_1st)) {
                // 解析字符串中的数据为对象并封装至list中返回给上一级
                String[] arr_1st = value_1st.split("==");
                for (String value_2st : arr_1st) {
                    String[] arr_2st = value_2st.split("=");
                    Cart item = new Cart();
                    item.setId(Long.parseLong(arr_2st[0])); //商品id
                    item.setType(arr_2st[1]); //商品类型ID
                    item.setName(arr_2st[2]); //商品名
                    item.setDescription(arr_2st[4]);//商品详情
                    item.setPrice(Integer.parseInt(arr_2st[3])); //商品市场价格
                    item.setNum(Integer.parseInt(arr_2st[5]));//加入购物车数量
                    item.setProduce(arr_2st[6]);//加入生产地址
                    items.add(item);
                }
            }
        }
        return items;

    }

    /**
     * 获取名为"cart"的cookie
     *
     * @param request
     * @return cookie
     */
    public Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cart_cookie = null;
        if (cookies.length <= 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            //获取购物车cookie
            if ("cart".equals(cookie.getName())) {
                cart_cookie = cookie;
            }
        }
        return cart_cookie;
    }

    /**
     * 制作cookie所需value
     *
     * @param cartVos 购物车列表
     * @return 解析为字符串的购物车列表，属性间使用"="相隔，对象间使用"=="相隔
     */
    public String makeCookieValue(List<Cart> cartVos) {
        StringBuffer buffer_2st = new StringBuffer();
        for (Cart item : cartVos) {
            buffer_2st.append(item.getId() + "=" + item.getType() + "="
                    + item.getName() + "=" + item.getPrice() + "="
                    + item.getDescription() + "=" + item.getNum() + "="
                    + item.getProduce() + "==");
        }
        return buffer_2st.toString().substring(0, buffer_2st.toString().length() - 2);
    }

     //查询购物车明细
    public List<CarGoods>  queryGoods(CarGoods carGoods){
        List<CarGoods> list = new ArrayList<>();
        try {
            list = carVGoodsService.queryList(carGoods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  list;

    }

    //查询所有订单
    public List<CarOrders>  queryAllOrders(){
        List<CarOrders> list = carVGoodsService.queryOrders();
        return  list;

    }
}
