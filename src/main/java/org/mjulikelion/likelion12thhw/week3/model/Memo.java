package org.mjulikelion.likelion12thhw.week3.model;


//@Getter
//@SuperBuilder
//@Setter
//public class Memo extends Entity {
//
//    private final UUID writerId;
//    @JsonIgnore //json으로 반환 안됨
//    private final List<Like> likeList = new LinkedList<>();
//    private String title;
//    private String content;
//
//    public void like(User user) {
//
//        Like like = findlikeByUser(user);
//        if (like != null) {
//            likeList.remove(like);
//        } else {
//            likeList.add(new Like(this.id, user.getId()));
//        }
//    }
//
//    private Like findlikeByUser(User user) {
//        return likeList.stream().filter(like -> like.isLikedByUser(user)).findAny().orElse(null);
//    }
//
//}

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "memo")
public class Memo extends BaseEntity {
    @Setter
    @Column(length = 100, nullable = false)// 길이는 100자 이하이고, 비어있을 수 없다.
    private String title;

    @Setter
    @Column(length = 2000, nullable = false)// 길이는 2000자 이하이고, 비어있을 수 없다.
    private String content;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "memo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // memo 필드를 기준으로 One To Many 관계를 맺는다. memo가 삭제되면 연관된 memo_like도 삭제된다. memo가 null이 되면 memo_like도 삭제된다. 지연로딩을 사용한다.
    private List<MemoLike> memoLikes;
}