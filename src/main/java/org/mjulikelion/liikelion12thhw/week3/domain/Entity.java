package org.mjulikelion.liikelion12thhw.week3.domain;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder //상속받은 클래스에서 Builder패턴 사용을 위함
public abstract class Entity {
    UUID id;
}
