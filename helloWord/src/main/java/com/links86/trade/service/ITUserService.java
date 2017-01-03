package com.links86.trade.service;

import com.links86.exception.Links86Exception;
import com.links86.trade.domain.TUser;
import com.links86.trade.domain.em.AccountType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-12 12:04
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public interface ITUserService {
    void save(TUser user) throws Links86Exception;
    TUser findById(long id) throws Links86Exception;
    TUser findByIdType(long id, AccountType type) throws Links86Exception;
    void delete(TUser obj) throws Links86Exception;
    Page<TUser> findAll(PageRequest pageRequest) throws Links86Exception;
    boolean exists(long id)throws Links86Exception;
    TUser findByOUID(long ouid)throws  Links86Exception;
}
