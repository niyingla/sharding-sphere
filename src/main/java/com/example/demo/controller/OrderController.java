package com.example.demo.controller;

import com.example.demo.entity.OrderVo;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p> OrderController </p>
 *
 * @author xiaoye
 * @version 1.0
 * @date 2022/4/18 15:49
 */
@RestController
public class OrderController {
  @Autowired
  private OrderService orderService;

  @GetMapping("/order")
  public List<OrderVo> getOrder() {
    List<OrderVo> orders = orderService.orderList();
    return orders;
  }

}
