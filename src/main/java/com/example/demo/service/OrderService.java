package com.example.demo.service;

import com.example.demo.dao.OrderMapper;
import com.example.demo.entity.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> OrderService </p>
 *
 * @author xiaoye
 * @version 1.0
 * @date 2022/4/18 15:47
 */
@Service
public class OrderService {

  @Autowired
  private OrderMapper orderMapper;

  public List<OrderVo> orderList() {
    List<OrderVo> orders = orderMapper.selectList();
    return orders;
  }

  public void saveProvince() {
    orderMapper.saveProvince();
  }

}
