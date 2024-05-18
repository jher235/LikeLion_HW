package org.mjulikelion.likelion12thhw.week3.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user")
public class User extends BaseEntity {
    @Setter
    @Column(length = 100, nullable = false, unique = true)// 길이는 100자 이하이고, 비어있을 수 없다. + 중복 불가
    private String email;

    @Setter
    @Column(length = 100, nullable = false)// 길이는 100자 이하이고, 비어있을 수 없다.
    private String name;

    @Setter
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // user 필드를 기준으로 One To Many 관계를 맺는다. user가 삭제되면 연관된 memo도 삭제된다. user가 null이 되면 memo도 삭제된다. 지연로딩을 사용한다.
    private List<Memo> memos;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // user 필드를 기준으로 One To Many 관계를 맺는다. user가 삭제되면 연관된 memo_like도 삭제된다. user가 null이 되면 memo_like도 삭제된다. 지연로딩을 사용한다.
    private List<MemoLike> memoLikes;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserOrganization> organizations;

    public void modify(String newEmail, String newName, String newPassword) {
        this.email = newEmail;
        this.name = newName;
        this.password = newPassword;
    }
}
