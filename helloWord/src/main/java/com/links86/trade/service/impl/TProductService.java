package com.links86.trade.service.impl;

import com.links86.trade.domain.TProduct;
import com.links86.trade.domain.em.AccountType;
import com.links86.trade.domain.em.ProductStatus;
import com.links86.trade.exception2.Links86Exception;
import com.links86.trade.service.BaseService;
import com.links86.trade.service.ITProductService;
import com.links86.util.UKUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-13 17:10
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Service
@Transactional
public class TProductService extends BaseService implements ITProductService {
    @Override
    public void save(TProduct product) throws Links86Exception {
        try {
            long cid = product.gettUser().getId();
            if (tUserRepo.findOne(cid).getType().equals(AccountType.COMPANY)) {
                tProductRepo.save(product);
            } else {
                throw new Links86Exception("企业信息输入错误！");
            }
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //上下架
    @Override
    public void upAndDown(long pid) throws Links86Exception {
        try {
            if (tProductRepo.exists(pid)) {
                TProduct product = tProductRepo.findOne(pid);
                ProductStatus productStatus = product.getStatus();
                if (productStatus.equals(ProductStatus.ON))
                    product.setStatus(ProductStatus.OFF);
                if (productStatus.equals(ProductStatus.OFF))
                    product.setStatus(ProductStatus.ON);
                tProductRepo.save(product);
            } else {
                throw new Links86Exception("产品信息输入错误！");
            }
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }


    @Override
    public TProduct findById(Long id) throws Links86Exception {
        try {
            return tProductRepo.findOne(id);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

//    @Override
//    public void delete(TProduct tProduct) throws Links86Exception {
//        try {
//            if (tProductRepo.exists(tProduct.getId())) {
//                tProduct.setProductStatus(ProductStatus.OFF);
//                tProductRepo.save(tProduct);
//            }
//        } catch (Links86Exception e) {
//            Links86Exception ex = new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e);
//            logger.info(ex.getMessage());
//            throw ex;
//        }
//
//    }

    /**
     * 删除下架的产品
     * 早上重写
     *
     * @throws Links86Exception
     */
    @Override
    @Transactional
    public void delete() throws Links86Exception {
        try {
            List<TProduct> tProducts = tProductRepo.findByStatus(ProductStatus.OFF);
            tProductRepo.delete(tProducts);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //通过产品名称模糊查询
    @Override
    public Page<TProduct> findByName(String name, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TProduct> specification =
                    (Root<TProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (name != null) {
                            predicates.add(cb.like(root.get("name"), '%' + name + '%'));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tProductRepo.findAll(specification, pageRequest);
//            return tServerRepo.findProductByName(name,pageRequest);

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //按企业id 分页查询所有的产品信息
    @Override
    public Page<TProduct> findByCompanyId(long comp_id, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TProduct> specification =
                    (Root<TProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tUserRepo.exists(comp_id)) {
                            predicates.add(cb.equal(root.get("tUser").get("id"), comp_id));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tProductRepo.findAll(specification, pageRequest);

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean exists(long id) throws com.links86.exception.Links86Exception {
        try {
            return tProductRepo.exists(id);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(long id) throws com.links86.exception.Links86Exception {
        try {
            tProductRepo.delete(id);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }
}
