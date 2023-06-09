package com.devex.SpringWebfluxJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devex.SpringWebfluxJpa.entity.RecentlyViewedProducts;

@Repository("recentlyViewedProductsRepository")
public interface RecentlyViewedProductsRepository extends JpaRepository<RecentlyViewedProducts, Integer>
{

}
