package org.mjulikelion.likelion12thhw.week3.repository;

import org.mjulikelion.likelion12thhw.week3.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

    boolean existsByName(String name);

    Optional<Organization> findByName(String name);


}
