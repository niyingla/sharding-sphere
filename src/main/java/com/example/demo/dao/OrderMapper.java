package com.example.demo.dao;

import com.example.demo.entity.OrderVo;

import java.util.List;

/**
 * <p> UserMapper </p>
 *
 * @author xiaoye
 * @version 1.0
 * @date 2022/4/18 15:44
 */
public interface OrderMapper {

  List<OrderVo> selectList();

}
