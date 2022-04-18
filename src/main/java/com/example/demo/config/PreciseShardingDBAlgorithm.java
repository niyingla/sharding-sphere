package com.example.demo.config;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * <p> PreciseShardingDBAlgorithm </p>
 *
 * @author xiaoye
 * @version 1.0
 * @date 2022/4/18 15:34
 */
public class PreciseShardingDBAlgorithm implements PreciseShardingAlgorithm<Integer> {
  /**
   * @param databaseNames 有效的数据源 或者 表 的名字  databaseNames 就为配置文件中的 配置的数据源信息 -> ds0 , ds1
   * @param shardingValue SQL 分片列 对应的实际值
   * @return
   */
  @Override
  public String doSharding(Collection<String> databaseNames, PreciseShardingValue<Integer> shardingValue) {
    /*
     * 作用：散列到具体的哪个库里面去
     * shardingValue ： SQL -> SELECT *  FROM t_order WHERE order _id IN(1,3,6)
     * shardingValue = [1,3,6]
     * */
    for (String each : databaseNames) {
      /**
       * 此方法如果参数所表示的字符序列是由该对象表示的字符序列的后缀返回true, 否则为false;
       *  请注意，如果参数是空字符串或等于此String对象由equals（Object）方法确定结果为 true。
       *  String Str = new String("This is really not immutable!!");   retVal = Str.endsWith( "immutable!!" )
       *  为true
       *  ds0.endsWith("0") -> true ;
       */
      if (each.endsWith(String.valueOf(shardingValue.getValue() % databaseNames.size()))) {
        //返回相应的数据库
        System.out.println("each" + each);
        return each;
      }
    }
    throw new UnsupportedOperationException();
  }

}
