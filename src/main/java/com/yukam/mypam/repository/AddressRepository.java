package com.yukam.mypam.repository;

import com.yukam.mypam.domain.Address;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Address entity.
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

}
