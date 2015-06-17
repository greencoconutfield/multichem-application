package com.yukam.mypam.repository;

import com.yukam.mypam.domain.Shipment;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Shipment entity.
 */
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

}
