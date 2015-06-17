package com.yukam.mypam.repository;

import com.yukam.mypam.domain.ActionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Quotation entity.
 */
public interface ActionDetailRepository extends JpaRepository<ActionDetail, Long> {

}
