package com.links86.trade.service;

import com.links86.exception.Links86Exception;
import com.links86.trade.domain.TProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


/**
 * Project name is links86-system
 * Created by xuli on 2016-09-13 17:09
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
public interface ITProductService {
    void save(TProduct product) throws Links86Exception;
    void upAndDown(long pid) throws Links86Exception;
    TProduct findById(Long id)throws Links86Exception;
    void delete()throws Links86Exception;
    void delete(long id)throws Links86Exception;
    Page<TProduct> findByName(String name, PageRequest pageRequest)throws Links86Exception;
    Page<TProduct> findByCompanyId(long comp_id, PageRequest pageRequest) throws Links86Exception;//按公司分页查询
    boolean exists(long id)throws Links86Exception;
}
