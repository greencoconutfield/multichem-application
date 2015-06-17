package com.yukam.mypam.repository;

import com.yukam.mypam.domain.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Contact entity.
 */
public interface ContactPersonRepository extends JpaRepository<ContactPerson, Long> {

}
