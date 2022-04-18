package com.example.demo.config;

import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p> RangeShardingDBAlgorithm </p>
 *
 * @author xiaoye
 * @version 1.0
 * @date 2022/4/18 15:34
 */
public class RangeShardingDBAlgorithm implements RangeShardingAlgorithm<Integer> {


  @Override
  public Collection<String> doSharding(final Collection<String> databaseNames,
                                       final RangeShardingValue<Integer> shardingValue) {

    /**
     * 自定义SQL -> SELECT *  FROM t_order WHERE order _id Between 2000 and 4000
     * ds0.t_order: 1000 ~ 3000
     * ds1.t_order: 3001 ~ 5000
     * ds2.t_order: 5001 ~ 7000
     *
     * 执行路由后的SQL 应为：
     * SELECT *  FROM ds0.t_order WHERE order _id Between 2000 and 3000
     * SELECT *  FROM ds1.t_order WHERE order _id Between 3001 and 4000
     */
    Set<String> result = new LinkedHashSet<>();
    // 从sql 中获取 Between 2000 and 4000   的值，将2000 赋值给 lower,  4000 赋值给 upper
    int lower = shardingValue.getValueRange().lowerEndpoint();
    int upper = shardingValue.getValueRange().upperEndpoint();
    for (int i = lower; i <= upper; i++) {
      for (String each : databaseNames) { //ds0,ds1
        if (each.endsWith(i % databaseNames.size() + "")) {
          result.add(each);
        }
      }
    }
    return result;
  }
}
