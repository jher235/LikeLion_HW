package org.mjulikelion.likelion12thhw.week3.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mjulikelion.likelion12thhw.week3.model.Organization;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetOrganizationsDto {
    List<Organization> organizations;
}
