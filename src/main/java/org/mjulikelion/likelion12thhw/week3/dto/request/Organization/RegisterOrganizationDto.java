package org.mjulikelion.likelion12thhw.week3.dto.request.Organization;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterOrganizationDto {

    @NotBlank(message = "단체명을 작성해주세요")
    private String name;

}
