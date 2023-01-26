package com.gaokd.online_education.utils;

import com.gaokd.online_education.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Jwt工具类（JsonWebToken）
 * 注意点:
 * 1、生成的token，是可以通过base64进行解密出明文信息
 * 2、base64进行解密出明文信息，修改再进行编码，则会解密失败
 * 3、无法作废已颁布的token，除非改token
 */
public class JWTUtils {
    /**
     * 过期时间 一周
     */
    private static final long EXPIRE = 60000 * 60 * 24 * 7;
    /**
     * 加密秘钥
     */
    private static final String SECRET = "asdkwncnjads";

    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "gaokd_online_education";
    /**
     * subject
     */
    private static final String SUBJECT = "gaokd";

    /**
     * 根据用户信息 生成令牌(加密)
     */
    public static String geneJsonWebToken(User user) {
        String token = Jwts.builder()
                .claim("head_img", user.getHeadImg()) //payload-负载
                .claim("id", user.getId())
                .claim("name", user.getName())
                .setSubject(SUBJECT)//设置主题
                .setIssuedAt(new Date())//设置下发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))//设置过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET)//签名(加密方式)，头部加密钥
                .compact();
        token = TOKEN_PREFIX+token;
        return token;
    }

    /**
     * 校验令牌（解密）
     */

    public static Claims checkJWT(String token){
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }
}