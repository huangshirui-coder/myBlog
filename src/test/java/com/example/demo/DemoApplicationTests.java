package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class DemoApplicationTests {
//    @Resource
//    GoodsMapper goodsMapper;
//
//    @Autowired
//    MessageSender messageSender;
//
//
//
//    @Test
//    void PasswordEncoderTest(){
//        // $2a$10$3MgDHjahqQACSZlsKBgjxeYi10SnPpfYJ847YgN2JttVZpBC3RIwK
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encoder1 = encoder.encode("123456");
//        String encoder2 = encoder.encode(encoder1);
//        System.out.println(encoder1);
//        System.out.println(encoder2);
//        System.out.println(encoder.matches("123456","$2a$10$3MgDHjahqQACSZlsKBgjxeYi10SnPpfYJ847YgN2JttVZpBC3RIwK"));
//    }
//
//    @Test
//    void contextLoads() {
//        Goods goods = new Goods();
//        goods.setName("商品F");
//        goods.setCategory("种类10");
//        goods.setQuantity(10);
//        goods.setPrice(14);
//        int result = goodsMapper.insert(goods);
//        System.out.println(result);
//    }
//
//    @Test
//    public void testDemo1(){
//        for (int i = 0; i < 10; i++) {
//            messageSender.sendMessage("我爱你第" + i + "遍");
//        }
//    }
//

}
