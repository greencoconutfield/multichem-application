package com.yukam.mypam.repository;

import com.yukam.mypam.domain.SubIndustrySector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Spring Data JPA repository for the IndustrySector entity.
 */
public interface SubIndustrySectorRepository extends JpaRepository<SubIndustrySector, Long> {

        @Query("select s from SubIndustrySector s where s.name not like '****'")
        List<SubIndustrySector> getAllExceptRoot();

        @Query("select s from SubIndustrySector s where (s.parent.id =?1) and (s.name  like '****')")
        List<SubIndustrySector> findRootSubIndustrySectorByParentId(Long parent_id);



}
