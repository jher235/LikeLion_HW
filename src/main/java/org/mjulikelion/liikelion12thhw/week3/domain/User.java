package org.mjulikelion.liikelion12thhw.week3.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder //상속받은 클래스에서 Builder패턴 사용을 위함
public class User extends Entity {

    private String name;
}
