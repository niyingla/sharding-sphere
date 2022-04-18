package com.example.demo.config;

import com.google.common.collect.Range;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <p> RangeShardingTableAlgorithm </p>
 *
 * @author xiaoye
 * @version 1.0
 * @date 2022/4/18 15:35
 */
public class RangeShardingTableAlgorithm implements RangeShardingAlgorithm<Integer> {

  @Override
  public Collection<String> doSharding(final Collection<String> tableNames,
                                       final RangeShardingValue<Integer> shardingValue) {
    Set<String> result = new LinkedHashSet<>();
    // 如果between  2000000 and 7000000
    for (String each : tableNames) {
      if (containRange(shardingValue, 0, 2000000) && each.endsWith("0")) {
        result.add(each);
      }
      if (containRange(shardingValue, 2000000, 7000000) && each.endsWith("1")) {
        result.add(each);
      }
      if (containRange(shardingValue, 7000000, Integer.MAX_VALUE) && each.endsWith("2")) {
        result.add(each);
      }
    }
    return result;
  }

  private boolean containRange(RangeShardingValue<Integer> shardingValue, int i, int maxValue) {
    return Range.closed(i, maxValue).contains(shardingValue.getValueRange().lowerEndpoint()) ||
        Range.closed(i, maxValue).contains(shardingValue.getValueRange().upperEndpoint());
  }

}
