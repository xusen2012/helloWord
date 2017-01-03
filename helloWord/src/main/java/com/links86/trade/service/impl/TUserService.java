package com.links86.trade.service.impl;

import com.links86.trade.domain.TUser;
import com.links86.trade.domain.em.AccountType;
import com.links86.trade.exception2.Links86Exception;
import com.links86.trade.service.BaseService;
import com.links86.trade.service.ITUserService;
import com.links86.util.UKUtil;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-12 12:06
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Service
@Transactional
public class TUserService extends BaseService implements ITUserService {


    @Override
    public void save(TUser user) throws Links86Exception {
        try {
            tUserRepo.save(user);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }


    @Override
    public TUser findById(long id) throws Links86Exception {
        try {
            return tUserRepo.findOne(id);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public TUser findByIdType(long id, AccountType type) throws Links86Exception {
        try {
            TUser tUser = new TUser();
            tUser.setId(id);
            tUser.setType(type);
            return tUserRepo.findOne(Example.of(tUser));
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(TUser obj) throws Links86Exception {
        try {
            tUserRepo.delete(obj);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }


    @Override
    public Page<TUser> findAll(PageRequest pageRequest) throws Links86Exception {
        try {
            return tUserRepo.findAll(pageRequest);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean exists(long id) throws Links86Exception {
        try {
            return tUserRepo.exists(id);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

    @Override
    public TUser findByOUID(long ouid) throws com.links86.exception.Links86Exception {
        try {
            return tUserRepo.findByOutUID(ouid);
        } catch (Links86Exception e) {
            logger.info(e.getMessage());
            throw e;
        }
    }

}
