package com.yukam.mypam.repository;

import com.yukam.mypam.domain.PreOrder;
        import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the PreOrder entity.
 */
public interface PreOrderRepository extends JpaRepository<PreOrder, Long> {

}
