package org.mjulikelion.liikelion12thhw.week3.model.example.repository;

import org.mjulikelion.liikelion12thhw.week3.model.example.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//DB와 상호작용, 제네릭은 <객체, PK>
public interface TeamRepository extends JpaRepository<Team, UUID> {

}
