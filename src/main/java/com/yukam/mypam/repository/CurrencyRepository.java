package com.yukam.mypam.repository;

import com.yukam.mypam.domain.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Currency entity.
 */
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
