package org.mjulikelion.likelion12thhw.authentication;

import lombok.Getter;
import lombok.Setter;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Setter
@Getter
@Component
@RequestScope
public class AuthenticationContext {
    private User principal;

}
