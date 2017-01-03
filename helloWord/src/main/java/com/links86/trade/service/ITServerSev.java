package com.links86.trade.service;

import com.links86.exception.Links86Exception;
import com.links86.trade.domain.TServer;
import com.links86.trade.domain.em.ServerStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.SavepointManager;


/**
 * Project name is links86-system
 * Created by xuli on 2016-09-13 23:25
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public interface ITServerSev {
    void add(TServer server) throws Links86Exception;
    void update(TServer server)throws Links86Exception;
    TServer findById(Long id)throws Links86Exception;
    void delete(TServer tServer)throws Links86Exception;
    Page<TServer> findByProductId(Long pid, ServerStatus status, PageRequest pageRequest)throws Links86Exception;
    Page<TServer> findByName(String name, PageRequest pageRequest)throws Links86Exception;
    boolean exists(long id)throws Links86Exception;
}
