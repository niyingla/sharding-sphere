package com.example.demo.controller;

import com.example.demo.entity.OrderVo;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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

  @GetMapping("/saveProvince")
  public void saveProvince() {
    orderService.saveProvince();
  }

  @GetMapping("/testThis")
  public String testThis(@RequestHeader("Authorization") String token) {
    System.out.println("对方实打实大苏打的方式 " + token);
    return "871212 TOKEN" + token;
  }

  @GetMapping("/douyin-pf-system/dyTenantAppConfig/getTenantConfig")
  public String testThis(@RequestHeader("Authorization") String token, @RequestHeader("storeId") Integer storeId) {
    System.out.println("对方实打实大苏打的方式 " + token + storeId);
    return "871212 TOKEN" + token;
  }

  @GetMapping("qiyuan/douyin-pf-system/dyTenantAppConfig/getTenantConfig")
  public String testThis1(@RequestHeader("Authorization") String token, @RequestHeader("storeId") Integer storeId) {
    System.out.println("对方实打实大苏打的方式 " + token + storeId);
    return "871212 TOKEN" + token;
  }


}
