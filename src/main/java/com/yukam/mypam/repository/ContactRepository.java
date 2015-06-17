package com.yukam.mypam.repository;

import com.yukam.mypam.domain.Contact;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Contact entity.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
