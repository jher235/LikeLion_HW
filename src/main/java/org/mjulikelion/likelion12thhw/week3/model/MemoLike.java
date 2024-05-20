package org.mjulikelion.likelion12thhw.week3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "memo_like")
public class MemoLike extends BaseEntity {
    //    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Memo memo;

    //    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
}