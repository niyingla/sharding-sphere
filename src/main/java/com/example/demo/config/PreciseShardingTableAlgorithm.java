package com.example.demo.config;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * <p> PreciseShardingTableAlgorithm </p>
 *
 * @author xiaoye
 * @version 1.0
 * @date 2022/4/18 15:35
 */
public class PreciseShardingTableAlgorithm implements PreciseShardingAlgorithm<Long> {
  /**
   * 注释键 PreciseShardingDBAlgorithm
   * @param tableNames
   * @param shardingValue
   * @return
   */
  @Override
  public String doSharding(Collection<String> tableNames,
                           PreciseShardingValue<Long> shardingValue) {
    for (String key : tableNames) {
      if (key.endsWith(String.valueOf(shardingValue.getValue() % tableNames.size()))) {
        System.out.println("key"+key);
        return key;
      }
    }
    throw new UnsupportedOperationException();
  }
}
