package org.mjulikelion.likelion12thhw.week3.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mjulikelion.likelion12thhw.week3.model.User;

@AllArgsConstructor
@Getter
public class UserResponse {
    private final String name;

    public UserResponse(User user) {
        this.name = user.getName();
    }
}
