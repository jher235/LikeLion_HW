package org.mjulikelion.likelion12thhw.week3.repository;

import org.mjulikelion.likelion12thhw.week3.model.Organization;
import org.mjulikelion.likelion12thhw.week3.model.User;
import org.mjulikelion.likelion12thhw.week3.model.UserOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserOrganizationRepository extends JpaRepository<UserOrganization, UUID> {

    Optional<UserOrganization> findByUserAndOrganization(User user, Organization organization);

    List<UserOrganization> findAllByUser(User user);
}
