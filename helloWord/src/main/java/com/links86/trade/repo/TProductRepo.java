package com.links86.trade.repo;

import com.links86.trade.domain.TProduct;
import com.links86.trade.domain.em.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-13 17:05
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Repository
@Transactional
public interface TProductRepo extends JpaRepository<TProduct, Long> {

    Page<TProduct> findAll(Specification<TProduct> specification, Pageable page);


//    @Query(value = "select new TProduct(t.id,t.name,t.status,t.servers[0].id,t.user_id) from TProduct t where t.name =:name")
//    Page<TProduct> findByName(@Param(value = "name")String name,Pageable page);

    List<TProduct> findByStatus(ProductStatus productStatus);
}
