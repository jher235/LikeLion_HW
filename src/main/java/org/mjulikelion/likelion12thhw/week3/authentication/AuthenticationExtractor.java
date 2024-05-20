package org.mjulikelion.likelion12thhw.week3.authentication;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.mjulikelion.likelion12thhw.week3.exception.AuthorizeException;
import org.mjulikelion.likelion12thhw.week3.exception.errorcode.ErrorCode;

@Log4j2
public class AuthenticationExtractor {
    private static final String TOKEN_COOKIE_NAME = "AccessToken";

    public static String extract(final HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (TOKEN_COOKIE_NAME.equals(c.getName())) {
                    log.info(JwtEncoder.decodeJwtBearerToken(c.getValue()));
                    return JwtEncoder.decodeJwtBearerToken(c.getValue());
                }
            }
        }
        throw new AuthorizeException(ErrorCode.TOKEN_NOT_FOUND);
    }
}
