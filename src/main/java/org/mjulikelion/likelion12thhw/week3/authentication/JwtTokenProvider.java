package org.mjulikelion.likelion12thhw.week3.authentication;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.mjulikelion.likelion12thhw.week3.exception.AuthorizeException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Component
public class JwtTokenProvider {
    private final SecretKey key;//시크릿 키
    private final long validityInMilliseconds;//유효 시간

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") final String secretKey,
                            @Value("${security.jwt.token.expire-length}") final long validityInMilliseconds) {
        //hmacShaKeyFor 메소드는 비밀 키를 안전하게 생성하는 데 필요한 길이와 알고리즘(HMAC SHA 알고리즘) 처리
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));//시크릿 키를 바이트 배열로 변환해줘야 함
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createToken(final String payload) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + validityInMilliseconds);//만료시간 = 현재 시간 + 유효 시간

        return Jwts.builder()
                .setSubject(payload) //토큰의 subject를 파라미터로 받은 payload(==userId)로 설정
                .setIssuedAt(now)//발급시간 설정
                .setExpiration(expiration)//만료시간
                .signWith(key, SignatureAlgorithm.HS256)//암호화한 키와 HS256 서명 알고리즘을 사용하여 토큰에 서명
                .compact();//위 설정을 바탕으로 JWT 토큰 생성, 문자열로 반환
    }

    public String getPayload(final String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)//시크릿 키로 토큰의 서명을 검증
                    .build()// 설정 완료된 파서 생성
                    .parseClaimsJws(token)//만료 시간 검증, 토큰 파싱
                    .getBody()//파싱된 토큰에서 body추출
                    .getSubject();//body에서 subject 추출
        } catch (JwtException e) {
            throw new AuthorizeException(ErrorCode.TOKEN_INVALID, e.getMessage());
        }
    }
}
