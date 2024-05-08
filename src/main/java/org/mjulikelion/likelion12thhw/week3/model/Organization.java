package org.mjulikelion.likelion12thhw.week3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "organization")
@Builder
public class Organization extends BaseEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<UserOrganization> userOrganization;

    @Column(length = 20, nullable = false, unique = true)
    String name;

}
