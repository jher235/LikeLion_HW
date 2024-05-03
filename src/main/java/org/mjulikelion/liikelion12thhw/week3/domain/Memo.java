package org.mjulikelion.liikelion12thhw.week3.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Getter
@SuperBuilder
@Setter
public class Memo extends Entity {

    private final UUID writerId;
    @JsonIgnore //json으로 반환 안됨
    private final List<Like> likeList = new LinkedList<>();
    private String title;
    private String content;

    public void like(User user) {

        Like like = islikedByUser(user);
        if (like != null) {
            likeList.remove(like);
        } else {
            likeList.add(new Like(this.id, user.getId()));
        }

    }

    private Like islikedByUser(User user) {
        return likeList.stream().filter(like -> like.isLikedByUser(user)).findAny().orElse(null);
    }


}
