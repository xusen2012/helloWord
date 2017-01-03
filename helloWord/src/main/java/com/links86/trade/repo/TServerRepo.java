package com.links86.trade.repo;

import com.links86.trade.domain.TProduct;
import com.links86.trade.domain.TServer;
import com.links86.trade.domain.em.ServerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-13 23:23
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Repository
@Transactional
public interface TServerRepo extends JpaRepository<TServer, Long> {

    //按给定条件完成精准查询或者模糊查询
    Page<TServer> findAll(Specification<TServer> specification, Pageable page);

    @Query(value = "select new TServer(t.id,t.name,t.balance,t.serverStatus,t.product.id,t.beginTime,t.endTime) " +
            "from TServer t where t.product.id = :productId and t.serverStatus = :serverStatus")
    Page<TServer> findAllByProdAndStatus(@Param(value = "productId") long productId,
                                         @Param(value = "serverStatus") ServerStatus serverStatus, Pageable pageable);

//    @Query(value = "select new TProduct(t.product.id,t.product.name,t.product.status,t.id,t.product.tUser.id) from TServer t where t.product.name =:name")
//    Page<TProduct> findProductByName(@Param(value = "name") String name, Pageable page);
}
