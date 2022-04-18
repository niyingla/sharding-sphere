package com.example.demo.entity;

import lombok.Data;

/**
 * <p> Order </p>
 *
 * @author xiaoye
 * @version 1.0
 * @date 2022/4/18 15:46
 */
@Data
public class OrderVo {
  private Integer orderId;

  private Integer count;

  private Integer userId;
}
