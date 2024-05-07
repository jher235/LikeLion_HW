package org.mjulikelion.likelion12thhw.week3.dto.response.Organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mjulikelion.likelion12thhw.week3.model.User;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUsersDto {
    List<User> users;
}
