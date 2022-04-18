package com.example.demo;

import com.example.demo.service.OrderService;
import io.shardingsphere.api.HintManager;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {
  @Autowired
  private OrderService orderService;

  @Test
  public void test() {
    // Hint分片策略必须要使用 HintManager工具类
    HintManager hintManager = HintManager.getInstance();
    hintManager.addDatabaseShardingValue("t_order", 0);
    hintManager.addTableShardingValue("t_order", 1);
    // 直接指定对应具体的数据库
    hintManager.setDatabaseShardingValue(1);
    //在读写分离数据库中，Hint 可以强制读主库（主从复制是存在一定延时，但在业务场景中，可能更需要保证数据的实时性）
    //hintManager.setMasterRouteOnly();
    System.out.println(orderService.orderList());
  }
}
