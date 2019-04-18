package com.volunteer.cache;

import redis.clients.jedis.Jedis;

public class Demo {

    public static void main(String[] args) {
        Jedis jedis= new Jedis("172.19.115.170",6379);//URL是我阿里云的IP
        jedis.auth("li123456");
        System.out.println(jedis.ping());
        System.out.println(jedis.get("mm"));
        System.out.println(jedis.set("steve","tao"));
    }
}
