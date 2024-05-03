package org.mjulikelion.liikelion12thhw.week3.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

//@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class Like {
    private final UUID memoId;
    private final UUID userId;
    private final LocalDateTime createdAt = LocalDateTime.now();

    public boolean isLikedByUser(User user) {
        System.out.println(user.getId() + " : " + this.userId);
        return user.getId().equals(this.userId);
    }

}
