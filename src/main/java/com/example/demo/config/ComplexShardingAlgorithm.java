package com.example.demo.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import io.shardingsphere.api.algorithm.sharding.ListShardingValue;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;

import java.util.*;

/**
 * <p> ComplexShardingAlgorithm </p>
 *
 * @author xiaoye
 * @version 1.0
 * @date 2022/4/18 20:36
 */
public class ComplexShardingAlgorithm implements ComplexKeysShardingAlgorithm {


  /**
   * 配置文件已经指定 user_id 分库 order_id 分表 但是这里还是要指定分库分表的规则
   * @param collection 在加载配置文件时，会解析表分片规则。将结果存储到 collection中，doSharding（）参数使用
   * @param shardingValues SQL中对应的
   * @return
   */
  @Override
  public Collection<String> doSharding(Collection<String> collection, Collection<ShardingValue> shardingValues) {
    System.out.println("collection:" + collection + ",shardingValues:" + shardingValues);

    Collection<Integer> orderIdValues = getShardingValue(shardingValues, "order_id");
    Collection<Integer> userIdValues = getShardingValue(shardingValues, "user_id");

    List<String> shardingSuffix = new ArrayList<>();

    // user_id，order_id分片键进行分表
    for (Integer userId : userIdValues) {
      for (Integer orderId : orderIdValues) {
        String suffix = userId % 2 + "_" + orderId % 2;
        for (String s : collection) {
          if (s.endsWith(suffix)) {
            shardingSuffix.add(s);
          }
        }
      }
    }

    return shardingSuffix;
  }

  /**
   * 例如: SELECT * FROM T_ORDER user_id = 100000 AND order_id = 1000009
   * 循环 获取SQL 中 分片键列对应的value值
   * @param shardingValues sql 中分片键的value值   -> 1000009
   * @param key 分片键列名                        -> user_id
   * @return shardingValues 集合                 -> [1000009]
   */
  private Collection<Integer> getShardingValue(Collection<ShardingValue> shardingValues, final String key) {
    Collection<Integer> valueSet = new ArrayList<>();
    Iterator<ShardingValue> iterator = shardingValues.iterator();
    while (iterator.hasNext()) {
      ShardingValue next = iterator.next();
      if (next instanceof ListShardingValue) {
        ListShardingValue value = (ListShardingValue) next;
        // user_id，order_id分片键进行分表
        if (value.getColumnName().equals(key)) {
          return value.getValues();
        }
      }
      if (next instanceof RangeShardingValue) {
        RangeShardingValue value = (RangeShardingValue) next;
        // user_id，order_id分片键进行分表
        return doSharding(Lists.newArrayList(0, 1), value);
      }

      if (next instanceof PreciseShardingValue) {
        PreciseShardingValue value = (PreciseShardingValue) next;
        System.out.println("value.getColumnName():" + value.getColumnName() + ",key:" + key);
      }
    }
    return valueSet;
  }

  public Collection<Integer> doSharding(Collection<Integer> tableNames,
                                        RangeShardingValue<Integer> shardingValue) {
    Set<Integer> result = new LinkedHashSet<>();
    // 如果between  2000000 and 7000000
    for (Integer each : tableNames) {
      if (containRange(shardingValue, 0, 2000000)) {
        result.add(0);
      }
      if (containRange(shardingValue, 2000000, 7000000)) {
        result.add(1);
      }
      if (containRange(shardingValue, 7000000, Integer.MAX_VALUE)) {
        result.add(2);
      }
    }
    return result;
  }

  private boolean containRange(RangeShardingValue<Integer> shardingValue, int i, int maxValue) {
    return Range.closed(i, maxValue).contains(shardingValue.getValueRange().lowerEndpoint()) ||
        Range.closed(i, maxValue).contains(shardingValue.getValueRange().upperEndpoint());
  }
}
