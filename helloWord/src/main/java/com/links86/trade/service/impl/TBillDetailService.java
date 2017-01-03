package com.links86.trade.service.impl;

import com.links86.exception.Links86Exception;
import com.links86.trade.domain.TAccountDetail;
import com.links86.trade.domain.TBillDetail;
import com.links86.trade.domain.to.RgetBillDetail;
import com.links86.trade.repo.TBillDetailRepo;
import com.links86.trade.service.BaseService;
import com.links86.trade.service.ITBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-12 12:06
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Service
@Transactional
public class TBillDetailService extends BaseService implements ITBillDetailService {

    @Autowired
    private TBillDetailRepo tBillDetailRepo;

    @Override
    public Page<TBillDetail> findAll(RgetBillDetail rgetBillDetail,PageRequest pageRequest) throws com.links86.exception.Links86Exception {
        try {
            Specification<TBillDetail> detailSpecification =
                    (Root<TBillDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if(rgetBillDetail.getBeginTime()!=null&&rgetBillDetail.getEndTime()!=null){
                            predicates.add(cb.between(root.get("time"),rgetBillDetail.getBeginTime(),rgetBillDetail.getEndTime()));
                        }
                        if(rgetBillDetail.getAccountId()!=0){
                            predicates.add(cb.equal(root.get("accountId"), rgetBillDetail.getAccountId()));
                        }
                        query.where(predicates.toArray(new Predicate[predicates.size()]));
                        query.orderBy(cb.desc(root.get("time")));
                        return query.getRestriction();
                        /*return cb.and(predicates.toArray(new Predicate[predicates.size()]));*/
                    };
            return tBillDetailRepo.findAll(detailSpecification, pageRequest);
        }catch (Links86Exception e){
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void save(TBillDetail tBillDetail) throws com.links86.exception.Links86Exception {
        try {
            tBillDetailRepo.save(tBillDetail);
        }catch (Links86Exception e){
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public BigDecimal computeExAmount(Long accountId) {
        try {
            return  tBillDetailRepo.computeExAmount(accountId);
        }catch (Links86Exception e){
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public BigDecimal computeInAmount(Long accountId) {
        try {
            return  tBillDetailRepo.computeInAmount(accountId);
        }catch (Links86Exception e){
            logger.info(e.getMessage());
            throw e;
        }
    }
}
