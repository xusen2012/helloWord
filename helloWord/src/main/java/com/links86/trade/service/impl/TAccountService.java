package com.links86.trade.service.impl;

import com.links86.trade.domain.em.AccountType;
import com.links86.trade.exception2.Links86Exception;
import com.links86.trade.domain.*;
import com.links86.trade.service.BaseService;
import com.links86.trade.service.ITAccountService;
import com.links86.util.UKUtil;
import org.springframework.data.domain.Example;
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
 * Created by xuli on 2016-09-13 16:14
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 * 预留service位置
 */
@Service
public class TAccountService extends BaseService implements ITAccountService {

    @Override
    @Transactional
    public void bindAccount(TAccount account) throws Links86Exception {
        try {
            if (tUserRepo.exists(account.gettUser().getId())) {
                tAccountRepo.save(account);
            } else {
                throw new Links86Exception("用户id信息输入有错误!");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void unpackAccount(long id) throws Links86Exception {
        try {
            if (tAccountRepo.exists(id)) {
                BigDecimal amount = tAccountDetailRepo.computeTotalAmount(id);
                if (amount == null || amount.compareTo(new BigDecimal(0)) > 0) {
                    TAccount tAccount = tAccountRepo.findOne(id);
                    tAccount.setFreeze(true);
                    System.out.println(tAccount);
                    tAccountRepo.save(tAccount);
                    System.out.println("33333333333");
                } else {
                    throw new Links86Exception("不能解除欠费账户的绑定关系！");
                }
            } else {
                throw new Links86Exception("此账户不存在");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    //重新审核,思路是无法递归所有问题，我就正面建立模版
    @Override
    @Transactional
    public void updateAccount(TAccount account) throws Links86Exception {

        try {
            if (tAccountRepo.exists(account.getId())) {
                tAccountRepo.save(account);
            } else {
                throw new Links86Exception("账户信息输入错误！");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public TAccount findById(long id) throws Links86Exception {
        try {
            return tAccountRepo.findOne(id);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public TAccount findByUserId(long uid, AccountType type) throws Links86Exception {
        try {
            if (tUserRepo.exists(uid)) {
                return tAccountRepo.findByUserId(uid, type);
            } else {
                throw new Links86Exception("用户id输入错误!");
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Page<TAccountAddress> findAddressByAId(long id, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TAccountAddress> accountAddressSpecification =
                    (Root<TAccountAddress> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tAccountRepo.exists(id)) {
                            predicates.add(cb.equal(root.get("tAccount").get("id"), id));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tAccountAddressRepo.findAll(accountAddressSpecification, pageRequest);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    //通过限定模版的方式
    @Override
    @Transactional
    public void addAddress(TAccountAddress accountAddress) throws Links86Exception {
        try {
            tAccountAddressRepo.save(accountAddress);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public TAccountAddress getDefaultAddress(long acc_id) throws Links86Exception {
        try {
            System.out.println(acc_id);
            return tAccountAddressRepo.findDefault(acc_id);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    //设置默认地址
    @Override
    @Transactional
    public void defaultAddress(TAccountAddress accountAddress) throws Links86Exception {
        try {
            TAccountAddress deafault = tAccountAddressRepo.findDefault(accountAddress.gettAccount().getId());
            if (deafault != null) {
                deafault.setDefault(false);
                tAccountAddressRepo.save(deafault);
            }
            tAccountAddressRepo.save(accountAddress);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    //删除地址重新审核
    @Override
    public void deleteAddress(long id) throws Links86Exception {
        try {
            TAccountAddress tAccountAddress = tAccountAddressRepo.findOne(id);
            if (tAccountAddress.isDefault()) {
                throw new Links86Exception("不能删除默认收获地址!");
            } else {
                tAccountAddressRepo.delete(id);
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    //重审代码
    @Override
    public TAccountAddress findAddressById(long aid) throws Links86Exception {
        try {
            return tAccountAddressRepo.findOne(aid);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    //重构新增流水
    @Override
    public void addAccountDetail(TAccountDetail tAccountDetail) throws Links86Exception {
        try {
            tAccountDetailRepo.save(tAccountDetail);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    //id查询带分页重新审核
    @Override
    public Page<TAccountDetail> findDetailByAId(long id, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TAccountDetail> detailSpecification =
                    (Root<TAccountDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tAccountRepo.exists(id)) {
                            predicates.add(cb.equal(root.get("tAccount").get("id"), id));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tAccountDetailRepo.findAll(detailSpecification, pageRequest);

        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    //id审核带分页带日期条件
    @Override
    public Page<TAccountDetail> findDetailByAId(long id, Date btime, Date etime, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TAccountDetail> specification =
                    (Root<TAccountDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tAccountRepo.exists(id)) {
                            predicates.add(cb.equal(root.get("tAccount").get("id"), id));
                        }
                        if (btime != null) {
                            try {
                                predicates.add(cb.greaterThanOrEqualTo(root.get("updatedTime"), btime));
                            } catch (Links86Exception e) {
                                logger.error(e.getMessage());
                                throw e;
                            }
                        }
                        if (etime != null) {
                            try {
                                predicates.add(cb.lessThanOrEqualTo(root.get("updatedTime"), etime));
                            } catch (Links86Exception e) {
                                logger.error(e.getMessage());
                                throw e;
                            }
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tAccountDetailRepo.findAll(specification, pageRequest);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public TAccountDetail findDetailByNumber(long number) throws Links86Exception {
        try {
            return tAccountDetailRepo.findByNumber(number);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public BigDecimal computeAccountAmount(long id) throws Links86Exception {
        try {
            return tAccountDetailRepo.computeTotalAmount(id);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void addAccountItem(TAccountItem tAccountItem) throws Links86Exception {
        try {
            long aid = tAccountItem.gettAccount().getId();
            long pid = tAccountItem.gettProduct().getId();
            if (tAccountRepo.exists(aid)) {
                if (tProductRepo.exists(pid)) {
                    tAccountItem.settProduct(tProductRepo.findOne(pid));
                    tAccountItem.settAccount(tAccountRepo.findOne(aid));
                    tAccountItemRepo.save(tAccountItem);
                }
            }
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

//    @Override
//    public void unuseAccountItem(TAccountItem tAccountItem) throws Links86Exception {
//        try {
//            if (tAccountRepo.exists(tAccountItem.gettAccount().getId())) {
//                //仅仅增加条目，不允许做其他操作
//                tAccountItem.settAccount(tAccountRepo.findById(tAccountItem.gettAccount().getId()));
//                if (tProductRepo.exists(tAccountItem.gettProduct().getId())) {
//                    tAccountItem.settProduct(tProductRepo.findOne(tAccountItem.gettProduct().getId()));
//                    tAccountItem.setInUse(true);
//                    if (tAccountItemRepo.exists(tAccountItem.getId())) {
//                        if(tAccountItem.)
//                    }
//
//                }
//            }
//        } catch (Links86Exception e) {
//            Links86Exception ex = new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e);
//            logger.info(ex.getMessage());
//            throw ex;
//        }
//    }

    @Override
    @Transactional
    public Page<TAccountItem> findItemByAccountId(long id, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TAccountItem> specification =
                    (Root<TAccountItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (tAccountRepo.exists(id)) {
                            predicates.add(cb.equal(root.get("tAccount").get("id"), id));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tAccountItemRepo.findAll(specification, pageRequest);

        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean exists(long id) throws Links86Exception {
        try {
            return tAccountRepo.exists(id);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean existAddress(long ad_id) throws Links86Exception {
        try {
            return tAccountAddressRepo.exists(ad_id);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean existDetail(long did) throws Links86Exception {
        try {
            return tAccountDetailRepo.exists(did);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean existItem(long iid) throws Links86Exception {
        try {
            return tAccountItemRepo.exists(iid);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
//    @Override
//    public TAccount findById(long id) throws Links86Exception {
//        try {
//            if (tAccountRepo.exists(id)) {
//                TAccount account = tAccountRepo.findOne(id);
//                if (account.isFreeze())
//                    throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), "此账户已经被冻结!");
//                return account;
//            } else {
//                throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), "输入了无效的账户id!");
//            }
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
//
//    @Override
//    public TAccountAddress findByAddressId(long id) throws Links86Exception {
//        try {
//            if (tAccountAddressRepo.exists(id)) {
//                return tAccountAddressRepo.findOne(id);
//            } else {
//                throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), "输入了无效的地址id!");
//            }
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
//
//    @Override
//    public void save(Object obj) throws Links86Exception {
//        try {
//            if (obj instanceof TCompanyAccount) {
//                TCompanyAccount companyAccount = (TCompanyAccount) obj;
//                tCompanyAccountRepo.save(companyAccount);
//            } else if (obj instanceof TUserAccount) {
//                TUserAccount userAccount = (TUserAccount) obj;
//                tUserAccountRepo.save(userAccount);
//            } else if (obj instanceof TAccount) {
//                TAccount account = (TAccount) obj;
//                tAccountRepo.save(account);
//            } else {
//                throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), "输入了未知的账户关联信息!请检查类型");
//            }
//
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//
//    }
//
//    @Override
//    public void save(TAccountDetail tAccountDetail) throws Links86Exception {
//        try {
//            tAccountDetailRepo.save(tAccountDetail);
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
//
//    @Override
//    public void save(TAccountAddress tAccountAddress) throws Links86Exception {
//        try {
//            tAccountAddressRepo.save(tAccountAddress);
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
//
//    @Override
//    public Page<TAccountDetail> findDetails(Long id, PageRequest pageRequest) throws Links86Exception {
//        try {
//            Specification<TAccountDetail> specification = (Root<TAccountDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//                List<Predicate> predicates = new ArrayList<>();
//                if (tAccountRepo.exists(id))
//                    predicates.add(cb.equal(root.get("tAccount").get("id"), id));
////                    predicates.add(cb.equal(root.get("tAccount").get("id"), id));
//                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//            };
//            return tAccountDetailRepo.findAll(specification, pageRequest);
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
//
//    @Override
//    public Page<TAccountAddress> findAddresses(Long id, PageRequest pageRequest) throws Links86Exception {
//        try {
//            Specification<TAccountAddress> specification = (Root<TAccountAddress> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//                List<Predicate> predicates = new ArrayList<>();
//                if (tAccountAddressRepo.exists(id))
//                    predicates.add(cb.equal(root.get("tAccount").get("id"), id));
//                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//            };
//            return tAccountAddressRepo.findAll(specification, pageRequest);
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
//
//    @Override
//    public List<TAccountAddress> findAddresses(Long id) throws Links86Exception {
//        try {
//            return tAccountAddressRepo.findAllByAccountId(id);
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
//
//    //此处该从关联的部分提前删
//    @Override
//    @Transactional
//    public void delete(long id) throws Links86Exception {
//        try {
//            if (tAccountRepo.exists(id)) {
//                List<TAccountDetail> tAccountDetails = tAccountDetailRepo.findAllByAccountId(id);
//                if (tAccountDetails.size() > 0) {
//                    for (TAccountDetail tAccountDetail : tAccountDetails)
//                        deleteDetail(tAccountDetail.getId());
//                }
//                List<TAccountAddress> tAccountAddresses = tAccountAddressRepo.findAllByAccountId(id);
//                if (tAccountAddresses.size() > 0) {
//                    for (TAccountAddress tAccountAddress : tAccountAddresses)
//                        deleteAddress(tAccountAddress.getId());
//                }
//                tAccountRepo.delete(id);
//            } else
//                throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), "id输入有误,未找到对应信息!");
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
//
//    //就该一条条的删，不要乱来
//    @Override
//    @Transactional
//    public void deleteDetail(long id) throws Links86Exception {
//        try {
//            tAccountDetailRepo.delete(id);
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
//
//    //就该一条条的删，不要乱来
//    @Override
//    @Transactional
//    public void deleteAddress(long id) throws Links86Exception {
//        try {
//            tAccountAddressRepo.delete(id);
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
//
//    @Override
//    public boolean existsAccount(long id) throws Links86Exception {
//        try {
//            return tAccountRepo.exists(id);
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
//
//    @Override
//    public boolean existsAccountAddress(long id) throws Links86Exception {
//        try {
//            return tAccountAddressRepo.exists(id);
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }

    //    @Override
//    public long findAccountMaxDetail(long id) throws Links86Exception {
//        try {
//            return tAccountDetailRepo.findAccountMaxDetail(id);
//        } catch (Links86Exception e) {
//            logger.error(e.getMessage());
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }

//    @Override
//    public BigDecimal computeTotalAmount(long id) throws Links86Exception {
//        try {
//            BigDecimal result = tAccountDetailRepo.computeTotalAmount(id);
//            if (result == null)
//                throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), "此账户尚未激活!");
//            return result;
//        } catch (Links86Exception e) {
//            ObjectOutput.exceptionOut(e,logger);
//            throw new Links86Exception(Links86Exception.TRADE_CODE, UKUtil.get32UUID(), e.getMessage());
//        }
//    }
}
