package org.mjulikelion.likelion12thhw.week3.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserResponseData {
    List<UserResponse> users;

}
