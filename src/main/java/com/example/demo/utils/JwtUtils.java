package com.example.demo.utils;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
// 此处的@Component和@ConfigurationProperties不起作用：可能与下文中static关键字有关（以后在处理吧）
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtils {

    //使用@ConfigurationProperties注解可以读取配置文件中的信息，只要在 Bean 上添加上了这个注解，指定好配置文件中的前缀，那么对应的配置文件数据就会自动填充到 Bean 的属性中
    private static long expire = 604800;
    private static String secret = "HRlELXqpSB";
    private static String header = "Authorization";

    // 生成JWT
    public static String generateToken(String username) {

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire);

        System.out.println(expire);
        System.out.println(header);
        System.out.println(secret);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)    // 7天过期
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // 解析JWT
    public static Claims getClaimsByToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // 判断JWT是否过期
    public static boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}
