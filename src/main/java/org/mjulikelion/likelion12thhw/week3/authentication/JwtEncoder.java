package org.mjulikelion.likelion12thhw.week3.authentication;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class JwtEncoder {
    public static String encodeJwtBearerToken(String token) {
        String BEARER = "Bearer ";
        //공백을 인코딩하기 위해 replaceAll("\\+", "%20")을 사용 공백은 아스키 코드로 %20이고
        // "Bearer "에서 공백이 +로 변경되기 때문에 이를 변환해주는 작업이 필요하다.
        return URLEncoder.encode(BEARER + token, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
    }

    public static String decodeJwtBearerToken(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8).substring(7);
    }
}
