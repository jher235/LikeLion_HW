package org.mjulikelion.likelion12thhw.week3.dto.response.Organization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.mjulikelion.likelion12thhw.week3.model.Organization;

@Getter
@Builder
@AllArgsConstructor
public class OrganizationResponse {
    private String name;

    public static OrganizationResponse organizationResponse(Organization organization) {
        return new OrganizationResponse(organization.getName());
    }
}
