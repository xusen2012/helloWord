package com.links86.trade.service.impl;

import com.links86.trade.exception2.Links86Exception;
import com.links86.trade.domain.TLoan;
import com.links86.trade.domain.TLoanDetail;
import com.links86.trade.service.BaseService;
import com.links86.trade.service.ITLoanService;
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
import java.util.Date;
import java.util.List;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-15 13:33
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Service
public class TLoanService extends BaseService implements ITLoanService {

    //新增子订单，如果订单编号不存在，则为新增，如果存在后续为追加或者修改
    @Override
    @Transactional
    public void newLoan(TLoanDetail tLoanDetail) throws Links86Exception {
        try {
            long ldid = tLoanDetail.getId();
            long sid = tLoanDetail.getSellerAccount().getId();
            long bdid = tLoanDetail.gettLoan().getBuyerAddress().getId();
            long pid = tLoanDetail.gettProduct().getId();

            if (tLoanDetailRepo.exists(ldid)) {
                throw new Links86Exception("新增子订单输入有错误！子订单已经存在");
            } else {
                if (tAccountAddressRepo.exists(bdid)) {
                    if (tAccountRepo.exists(sid) && tProductRepo.exists(pid)) {
                        tLoanDetailRepo.save(tLoanDetail); //存储子订单
                    } else {
                        throw new Links86Exception("店铺和产品信息输入有错误！");
                    }
                } else {
                    throw new Links86Exception("订单账户信息输入有错误！");
                }
            }

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //更新订单,只更新主订单的动作
    @Override
    public void updateLoan(TLoan tLoan) throws Links86Exception {
        try {
            long id = tLoan.getId();
            long bdid = tLoan.getBuyerAddress().getId();
            if (tAccountAddressRepo.exists(bdid))
                tLoanRepo.save(tLoan); //存储订单
            else {
                throw new Links86Exception("账户或地址输入有错误！");
            }
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //新增且更新 子订单条目,此条适合更新和新增已有的子订单
    @Override
    @Transactional
    public void updateLoanDetail(TLoanDetail tLoanDetail) throws Links86Exception {
        try {
            tLoanDetailRepo.save(tLoanDetail);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteLoan(long id) throws com.links86.exception.Links86Exception {
        try {
            tLoanRepo.delete(id);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public TLoan findById(long id) throws Links86Exception {
        try {
            return tLoanRepo.findOne(id);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public TLoanDetail findByLId(long ldid) throws Links86Exception {
        try {
            return tLoanDetailRepo.findOne(ldid);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //根据订单查询子订单
    @Override
    public Page<TLoanDetail> findByLoanId(long loanId, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TLoanDetail> specification =
                    (Root<TLoanDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tLoanRepo.exists(loanId)) {
                            predicates.add(cb.equal(root.get("tLoan").get("id"), loanId));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tLoanDetailRepo.findAll(specification, pageRequest);

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //通过买家id查询订单流水信息
    @Override
    public Page<TLoanDetail> findDetailByBuyer(long buyerId, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TLoanDetail> specification =
                    (Root<TLoanDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tAccountRepo.exists(buyerId)) {
                            predicates.add(cb.equal(root.get("tLoan").get("buyerAddress").get("tAccount").get("id"), buyerId));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tLoanDetailRepo.findAll(specification, pageRequest);

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }


    //根据买家账户和订单状态分页查询
    @Override
    public Page<TLoanDetail> findDetailByBuyer(long buyerId, String loanStatus, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TLoanDetail> specification =
                    (Root<TLoanDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tAccountRepo.exists(buyerId)) {
                            predicates.add(cb.equal(root.get("tLoan").get("buyerAddress").get("tAccount").get("tUser").get("id"), buyerId));
                        }
                        if (loanStatus != null) {
                            predicates.add(cb.equal(root.get("tLoan").get("status"), loanStatus));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tLoanDetailRepo.findAll(specification, pageRequest);

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //根据订单号和订单状态 查询子订单
    @Override
    public Page<TLoanDetail> findByLoanId(long loanId, String loanStatus, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TLoanDetail> specification =
                    (Root<TLoanDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tLoanRepo.exists(loanId)) {
                            predicates.add(cb.equal(root.get("tLoan").get("id"), loanId));
                        }
                        if (loanStatus != null) {
                            predicates.add(cb.equal(root.get("tLoan").get("status"), loanStatus));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tLoanDetailRepo.findAll(specification, pageRequest);

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //通过卖家id和起末时间查询
    @Override
    public Page<TLoanDetail> findDetailByBuyer(long buyerId, Date btime, Date etime, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TLoanDetail> specification =
                    (Root<TLoanDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tAccountRepo.exists(buyerId)) {
                            predicates.add(cb.equal(root.get("tLoan").get("buyerAddress").get("tAccount").get("tUser").get("id"), buyerId));
                        }
                        if (btime != null) {
                            predicates.add(cb.greaterThanOrEqualTo(root.get("tLoan").get("createdTime"), btime));
                        }
                        if (etime != null) {
                            predicates.add(cb.lessThanOrEqualTo(root.get("tLoan").get("createdTime"), etime));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tLoanDetailRepo.findAll(specification, pageRequest);

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //通过 订单id，开始时间，结束时间分页查询订单明细
    @Override
    public Page<TLoanDetail> findByLoanId(long loanId, Date btime, Date etime, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TLoanDetail> specification =
                    (Root<TLoanDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tLoanRepo.exists(loanId)) {
                            predicates.add(cb.equal(root.get("tLoan").get("id"), loanId));
                        }
                        if (btime != null) {
                            predicates.add(cb.greaterThanOrEqualTo(root.get("tLoan").get("createdTime"), btime));
                        }
                        if (etime != null) {
                            predicates.add(cb.lessThanOrEqualTo(root.get("tLoan").get("createdTime"), etime));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tLoanDetailRepo.findAll(specification, pageRequest);

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //通过买家id，开始时间，结束时间，状态，进行分页查询
    @Override
    public Page<TLoanDetail> findDetailByBuyer(long buyerId, Date btime, Date etime, String loanStatus, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TLoanDetail> specification =
                    (Root<TLoanDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tAccountRepo.exists(buyerId)) {
                            predicates.add(cb.equal(root.get("tLoan").get("buyerAddress").get("tAccount").get("tUser").get("id"), buyerId));
                        }
                        if (btime != null) {
                            predicates.add(cb.greaterThanOrEqualTo(root.get("tLoan").get("createdTime"), btime));
                        }
                        if (etime != null) {
                            predicates.add(cb.lessThanOrEqualTo(root.get("tLoan").get("createdTime"), etime));
                        }
                        if (loanStatus != null) {
                            predicates.add(cb.equal(root.get("tLoan").get("status"), loanStatus));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tLoanDetailRepo.findAll(specification, pageRequest);

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //通过订单id，和开始时间，结束时间，状态来查询
    @Override
    public Page<TLoanDetail> findByLoanId(long loanId, Date btime, Date etime, String loanStatus, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TLoanDetail> specification =
                    (Root<TLoanDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tLoanRepo.exists(loanId)) {
                            predicates.add(cb.equal(root.get("tLoan").get("id"), loanId));
                        }
                        if (btime != null) {
                            predicates.add(cb.greaterThanOrEqualTo(root.get("tLoan").get("createdTime"), btime));
                        }
                        if (etime != null) {
                            predicates.add(cb.lessThanOrEqualTo(root.get("tLoan").get("createdTime"), etime));
                        }
                        if (loanStatus != null) {
                            predicates.add(cb.equal(root.get("tLoan").get("status"), loanStatus));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tLoanDetailRepo.findAll(specification, pageRequest);

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    /**
     * @author xusen
     * @param beginTime
     * @param endTime
     * @param buyerId
     * @param sellerId
     * @param pageRequest
     * @return
     * @throws com.links86.exception.Links86Exception
     */
    @Override
    public Page<TLoanDetail> findLoanAndDetail(Date beginTime, Date endTime,String selectParam,long buyerId, long sellerId, PageRequest pageRequest) throws com.links86.exception.Links86Exception {
        try {
            Specification<TLoanDetail> specification =
                    (Root<TLoanDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if(beginTime!=null&endTime!=null){
                            predicates.add(cb.between(root.get("tLoan").get("createdTime"),beginTime,endTime));
                        }
                        if(selectParam!=null&&!"".equals(selectParam)){
                            predicates.add(cb.or(cb.equal(root.get("tLoan").get("id").as(String.class),selectParam),cb.like(root.get("tProduct").get("name"), "%" + selectParam + "%"), cb.like(root.get("sellerAccount").get("name"), "%"+ selectParam +"%"),cb.like(root.get("tLoan").get("buyerAddress").get("tAccount").get("name"), "%"+ selectParam +"%")));
                        }
                        if (buyerId!=0 || sellerId!=0) {
                            predicates.add(cb.or(cb.equal(root.get("tLoan").get("buyerAddress").get("tAccount").get("id"), buyerId),cb.equal(root.get("sellerAccount").get("id"), sellerId) ));
                        }
                        query.where(predicates.toArray(new Predicate[predicates.size()]));
                        query.orderBy(cb.desc(root.get("tLoan").get("updatedTime")));
                        return query.getRestriction();
                        /*return cb.and(predicates.toArray(new Predicate[predicates.size()]));*/
                    };
            Page<TLoanDetail> list=tLoanDetailRepo.findAll(specification,pageRequest);
            return list;
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    //删除子订单
    @Override
    public void deleteDetail(TLoanDetail tLoanDetail) throws Links86Exception {
        try {
            tLoanDetailRepo.delete(tLoanDetail);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean exists(long lid) throws Links86Exception {
        try {
            return tLoanRepo.exists(lid);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean existDetail(long ldid) throws Links86Exception {
        try {
            return tLoanDetailRepo.exists(ldid);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public BigDecimal computeTotalAmount(long loanId) throws Links86Exception {
        try {
            BigDecimal amount = tLoanDetailRepo.computeTotalAmount(loanId);
            return amount;
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public long findSellerId(long loanId) throws Links86Exception {
        try {
            return tLoanDetailRepo.findSellerId(loanId);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }
}
