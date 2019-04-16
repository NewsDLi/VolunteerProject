package com.volunteer.cache.manager;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CacheManager {

    /**
     * 按照默认的方式生成map的field 其中的obj最后都会调用toString()方法
     *
     * @param obj
     * @return
     */
    String generateMapFieldByDefault(Object...obj);

    /**
     * 保存一个对象 方法中会序列化对象成字符串
     *
     * @param key
     * @param obj
     */
    <T> void setObject(String key,T obj);

    /**
     * 保存一个对象 方法中会序列化对象成字符串
     *
     * @param key
     * @param t
     * @param expireSeconds
     *            过期时间
     */
    <T> void setObject(String key,T t,Integer expireSeconds);

    /**
     * 获取一个对象 反序列化字符串
     *
     * @param key
     * @return 对象
     */
    <T> T getObject(String key);

    /**
     * 保存字符串到缓存中
     *
     * @param key
     * @param value
     *            字符串值
     * @param expireSeconds
     *            过期时间(秒)
     */
    void setValue(String key,String value,Integer expireSeconds);

    /**
     * 保存字符串到缓存中(没有过期时间)
     *
     * @param key
     * @param value
     */
    void setValue(String key,String value);

    /**
     * sadd方式保存
     *
     * @param key
     * @param value
     * @return
     */
    Long addSetValue(String key,String value,Integer expireSeconds);

    /**
     * 得到集合的数量
     *
     * @param key
     * @return
     */
    Long getSetLength(String key);

    /**
     * 通过key移除数据
     *
     * @param key
     * @return
     */
    Long remove(String key);

    /**
     * 通过key获取数据
     *
     * @param key
     * @return
     */
    String getValue(String key);

    /**
     * 通过key以及字段名称，保存字符到一个map中 使用时通过key与field来进行获取
     *
     * @param key
     * @param field
     * @param value
     */
    void setMapValue(String key,String field,String value,int seconds);

    /**
     * 通过key以及map的field来获取具体的值
     *
     * @param key
     * @param field
     * @return
     */
    String getMapValue(String key,String field);

    /**
     * 删除某个key的map对应field的值
     *
     * @param key
     * @param field
     * @return
     */
    void removeMapValue(String key,String field);

    /**
     * 通过key以及字段名称，保存对象到一个map中 使用时通过key与field来进行获取
     *
     * @param key
     * @param field
     * @param t
     * @param seconds
     *            有效期时间
     */
    <T> void setMapObject(String key,String field,T t,int seconds);

    /**
     * 通过key以及map的field来获取具体的对象值
     *
     * @param key
     * @param field
     * @return
     */
    <T> T getMapObject(String key,String field);

    /**
     * 通过key获取整个map
     *
     * @param key
     * @return
     */
    Map<String, String> getAllMap(String key);

    /**
     * 将当前字符串数组插入到队列的头部
     *
     * @param key
     * @param values
     *            字符串数组
     */
    void pushToListHead(String key,String[] values);

    /**
     * 将当前字符串插入到队列的头部
     *
     * @param key
     * @param value
     */
    void pushToListHead(String key,String value);

    /**
     * 将当前字符串数组插入到队列的尾部
     *
     * @param key
     * @param values
     */
    void pushToListFooter(String key,String[] values);

    /**
     * 将当前字符串插入到队列的尾部
     *
     * @param key
     * @param value
     */
    void pushToListFooter(String key,String value);

    /**
     * 取出并返回队列头部的数据元素 会在队列中删除返回的元素
     *
     * @param key
     * @return
     */
    String popListHead(String key);

    /**
     * 取出并返回队到尾部的数据元素 会在队列中删除返回的元素
     *
     * @param key
     * @return
     */
    String popListFooter(String key);

    /**
     * 返回某个index的队列元素
     *
     * @param key
     * @param index
     * @return
     */
    String findListItem(String key,long index);

    /**
     * 获取队列数据，通过start以及end,如果想获取全部数据，start填写0,end填写maxLong的值
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> findLists(String key, long start, long end);

    /**
     * 返回队列中元素数量
     *
     * @param key
     * @return
     */
    long listLen(String key);

    /**
     * 添加字符串数组到set中
     *
     * @param key
     * @param values
     */
    void addSet(String key,String[] values);

    /**
     * 从set中返回一个元素
     *
     * @param key
     * @return
     */
    String popSet(String key);

    /**
     * set中是否已存在某个值
     *
     * @param key
     * @param member
     * @return
     */
    boolean existsInSet(String key,String member);

    /****
     * 从set中移除 指定value
     *
     * @param key
     * @param values
     * @return
     */
    Long removeFromSet(String key,String[] values);

    /**
     * 获取整个set
     *
     * @param key
     * @return
     */
    Set<String> findSetAll(String key);

    /**
     * 获取set的数量
     *
     * @param key
     * @return
     */
    long findSetCount(String key);

    /**
     * 添加value到一个sortset中
     *
     * @param key
     * @param value
     * @param sortNo
     */
    void addSortSet(String key,String value,long sortNo);

    /**
     * 删除通过key删除sortset的数据
     * @param key
     * @param window 时间窗
     */
    void removeSortSet(String key, long window);

    /**
     * 获取sortSet中sortNo为start到end的数据
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<String> findSortSets(String key,long start,long end);

    /**
     * 获取sortSet的数量
     *
     * @param key
     * @return
     */
    long findSortSetCount(String key);

    /**
     * 获取sortSet数量，通过sortNo的min到max进行筛选定位
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    long findSortSetCount(String key,long min,long max);

    /**
     * 计数器减少一个数量
     */
    Long Decr(String key,long value);

    /**
     * 计数器增加一个数量
     */
    Long Incr(String key,long value);

    /**
     * Rolling Time Window, 用于控制用户在某个时间窗口内的访问次数，
     * 比如：发送短信业务，5分钟内可以发送2次，24小时内可以发送5次
     * 如果验证通过后需要将本次调用添加进去
     * 基于redis集合特性实现
     * @param key
     * @param limit 限制次数
     * @param window 时间窗
     * @return　验证结果
     */
    boolean applyRollingTimeWindow(String key, long limit, long window);

    /**
     * @Title getExpireSecondsByKey
     * @Description 得到redis key的有效期
     * @param key
     * @return Long
     * @author Jamel.li
     * @date 2017年9月6日 下午6:34:00
     */
    Long ttl(String key);

    /**
     * @Description 通过key以及字段名称，保存字符到一个map中 使用时通过key与field来进行获取，不进行序列化，和过期时间的设置
     * @param key
     * @param field
     * @param value
     * @param jredis
     * @author shenshen.li
     * @date 2019年2月15日18:51:18
     */
    void setSimpleMapValue(String key,String field,String value, Jedis jredis);

    /**
     * @Description 通过key以及map的field来获取具体的值 ，不进行反序列化
     * @param key
     * @param field
     * @return String
     * @author shenshen.li
     * @date 2019年2月15日18:51:25
     */
    String getSimpleMapValue(String key,String field);

    /**
     * @Description 获取Jedis
     * @return Jedis
     * @author shenshen.li
     * @date 2019年2月15日18:51:25
     */
    Jedis getSimpleJedis();

    /**
     * 还回redis
     * @param jredis
     * @author shenshen.li
     * @date 2019年2月15日18:51:25
     */
    void returnSimpleResource(Jedis jredis);

}
