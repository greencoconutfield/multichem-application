package com.yukam.mypam.repository;

import com.yukam.mypam.domain.IndustrySector;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the IndustrySector entity.
 */
public interface IndustrySectorRepository extends JpaRepository<IndustrySector, Long> {

//    @Query("select u from IndustrySector u where u.parent.id = id")
//    List<IndustrySector> findSubSectors(Long id);

}
