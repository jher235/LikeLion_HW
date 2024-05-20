package org.mjulikelion.likelion12thhw.week3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Entity(name = "user_organization")
public class UserOrganization extends BaseEntity {
    //EAGER이거나 @JsonIgnore 둘 중 하나가 필요하다.
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    User user;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    Organization organization;
}
