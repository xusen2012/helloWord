package com.links86.trade.service.impl;

import com.links86.trade.domain.TServer;
import com.links86.trade.domain.em.ServerStatus;
import com.links86.trade.exception2.Links86Exception;
import com.links86.trade.service.BaseService;
import com.links86.trade.service.ITServerSev;
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
 * Created by xuli on 2016-09-13 23:26
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Service
@Transactional
public class TServerSev extends BaseService implements ITServerSev {
    @Override
    public void add(TServer server) throws Links86Exception {
        try {
            long pid = server.getProduct().getId();
            if (tProductRepo.exists(pid)) {
                server.setProduct(tProductRepo.findOne(pid));
                tServerRepo.save(server);
            } else {
                throw new Links86Exception("产品信息输入错误！");
            }
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void update(TServer server) throws Links86Exception {
        try {
            long sid = server.getId();
            long pid = server.getProduct().getId();
            if (tServerRepo.exists(sid)) {
                if (tProductRepo.exists(pid)) {
                    tServerRepo.save(server);
                } else {
                    throw new Links86Exception("产品信息输入错误！");
                }
            } else {
                throw new Links86Exception("服务信息输入错误！");
            }
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public TServer findById(Long id) throws Links86Exception {
        try {
            return tServerRepo.findOne(id);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }


    @Override
    public void delete(TServer tServer) throws Links86Exception {
        try {
            tServerRepo.delete(tServer);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    // 重新修订了方法
    @Override
    public Page<TServer> findByProductId(Long pid, ServerStatus status, PageRequest pageRequest) throws Links86Exception {
        try {
            return tServerRepo.findAllByProdAndStatus(pid, status, pageRequest);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }


    @Override
    public Page<TServer> findByName(String name, PageRequest pageRequest) throws Links86Exception {
        try {
            Specification<TServer> specification =
                    (Root<TServer> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
                        List<Predicate> predicates = new ArrayList<>();
                        if (name != null) {
                            predicates.add(cb.like(root.get("name"), '%' + name + '%'));
                        }
                        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                    };
            return tServerRepo.findAll(specification, pageRequest);

        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean exists(long id) throws Links86Exception {
        try {
            return tServerRepo.exists(id);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }
}
