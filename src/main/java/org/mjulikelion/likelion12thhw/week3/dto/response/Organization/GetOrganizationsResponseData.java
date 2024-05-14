package org.mjulikelion.likelion12thhw.week3.dto.response.Organization;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetOrganizationsResponseData {
    List<OrganizationResponse> organizationResponses;
}
