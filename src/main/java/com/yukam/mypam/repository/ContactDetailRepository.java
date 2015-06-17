package com.yukam.mypam.repository;

import com.yukam.mypam.domain.ContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Contact entity.
 */
public interface ContactDetailRepository extends JpaRepository<ContactDetail, Long> {

}
