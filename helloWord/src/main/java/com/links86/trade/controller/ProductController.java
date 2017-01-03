package com.links86.trade.controller;

import com.links86.trade.domain.TProduct;
import com.links86.trade.domain.TServer;
import com.links86.trade.domain.em.ProductStatus;
import com.links86.trade.domain.em.ServerStatus;
import com.links86.trade.domain.to.*;
import com.links86.trade.exception2.Links86Exception;
import com.links86.trade.utils.ObjectOutput;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Project name is links86-system
 * Created by xuli on 2016-09-15 14:08
 * my phone number is +86 15171494727
 * my google plus account is leoricxu@gmail.com
 * my qq number is 16982916
 */
@Controller
public class ProductController extends BaseController {

    //新增产品,此处新增和修改的时候，需要提供完整的属性，后续如果需要优化，应该只针对需要的属性进行封装
    @RequestMapping(value = "/trade/save_prod_snap", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse savaProdSnap(@RequestBody RsaveProduct product) {
        try {
            TProduct tProduct = new TProduct();
            if (product.getId() == 0) {
                tProduct.setId(snowflake.next());
                tProduct.setName(product.getName());
                if (checkUser(product.getCompanyId())) {
                    tProduct.settUser(itUserService.findById(product.getCompanyId()));
                } else {
                    throw new Exception("企业id输入有错误!");
                }
                if (checkProductStatus(product.getStatus()))
                    tProduct.setStatus(ProductStatus.valueOf(product.getStatus()));
                itProductService.save(tProduct);
                return buildSuccess().addObject("data", "新建成功").addObject("id",tProduct.getId());
            } else if (!itProductService.exists(product.getId())) {
                throw new Exception("产品信息不存在!");
            } else {
                tProduct.setId(product.getId());
                tProduct.setName(product.getName());
                if (checkUser(product.getCompanyId())) {
                    tProduct.settUser(itUserService.findById(product.getCompanyId()));
                } else {
                    throw new Exception("企业id输入有错误!");
                }
                if (checkProductStatus(product.getStatus()))
                    tProduct.setStatus(ProductStatus.valueOf(product.getStatus()));
                itProductService.save(tProduct);
                return buildSuccess().addObject("data", "修改成功!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //上下架产品
    @RequestMapping(value = "/trade/up_prod_snap", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse upProdSnap(@RequestBody RsaveProduct product) {
        try {
            long id = product.getId();
            if (checkProduct(id)) {
                itProductService.upAndDown(id);
            } else {
                throw new Exception("输入的产品id有错误!");
            }
            return buildSuccess();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }


    //查询
    @RequestMapping(value = "/trade/find_prod_snap", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse findProdSnap(@RequestBody RsaveProduct product) {
        try {
            long id = product.getId();
            if (checkProduct(id)) {
                return buildSuccess().addObject("data", itProductService.findById(id));
            } else {
                throw new Exception("输入的产品id有错误!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }


    //新增或修改服务  所有更新的部分所有的重新set 需要判断空
    @RequestMapping(value = "/trade/save_serv_snap", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse saveSevSnap(@RequestBody RsaveServer server) {
        try {
            TServer tServer = new TServer();
            if (checkProduct(server.getProductId())) {
                if (server.getId() == 0) {
                    tServer.setId(snowflake.next());
                    tServer.setName(server.getName());
                    tServer.setBalance(server.getBalance());
                    tServer.setBeginTime(server.getBeginTime());
                    tServer.setEndTime(server.getEndTime());
                    if (checkProduct(server.getProductId()))
                        tServer.setProduct(itProductService.findById(server.getProductId()));
                    else
                        throw new Exception("产品id输入有错误!");
                    if (checkServerstatus(server.getServerStatus()))
                        tServer.setServerStatus(ServerStatus.valueOf(server.getServerStatus()));
                    itServerSev.add(tServer);
                    return buildSuccess().addObject("data", "新建成功!");
                } else if (!checkServer(server.getId())) {
                    throw new Exception("服务信息不存在!");
                } else {
                    if (checkProduct(server.getProductId()))
                        tServer.setProduct(itProductService.findById(server.getProductId()));
                    else
                        throw new Exception("产品id输入有错误!");
                    tServer = itServerSev.findById(server.getId());
                    tServer.setName(server.getName());
                    tServer.setBalance(server.getBalance());
                    tServer.setBeginTime(server.getBeginTime());
                    tServer.setEndTime(server.getEndTime());
                    if (checkServerstatus(server.getServerStatus()))
                        tServer.setServerStatus(ServerStatus.valueOf(server.getServerStatus()));
                    itServerSev.add(tServer);
                    return buildSuccess().addObject("data", "更新成功");
                }
            } else {
                throw new Exception("找不到对应的产品id!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //删除下架产品，请谨慎使用   此处设置jpa的属性设置的比较大删除的时候可能会导致级联删除!
    @RequestMapping(value = "/trade/del_prod_snap", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse delProdSnap() {
        try {
            itProductService.delete();
            return buildSuccess();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }


    //按产品name获取所有产品列表
    @RequestMapping(value = "/trade/find_prod_by_name", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse findProdByName(@RequestBody RgetProduct prod) {
        setPageRequest(prod.getPageIndex(), prod.getPageSize(), prod.getDirection(), prod.getOrderFields());
        try {
            Page<TProduct> products = itProductService.findByName(prod.getName(), pageRequest);
            return buildSuccess().addObject("data", products);
        } catch (Links86Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //按公司id获取所有产品列表
    @RequestMapping(value = "/trade/find_prod_by_comany", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse findProdByCompany(@RequestBody RgetProduct prod) {
        setPageRequest(prod.getPageIndex(), prod.getPageSize(), prod.getDirection(), prod.getOrderFields());
        try {
            if (checkCompany(prod.getCompanyId())) {
                Page<TProduct> tProducts = itProductService.findByCompanyId(prod.getCompanyId(), pageRequest);
                return buildSuccess().addObject("data", tProducts);
            } else {
                throw new Exception("企业代码输入有错误!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //按模糊条件获取所有产品列表

    //获取具体服务的信息
    @RequestMapping(value = "/trade/find_serv_by_id", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse findServById(@RequestBody RgetServer server) {
        try {
            if (checkServer(server.getId())) {
                return buildSuccess().addObject("data", itServerSev.findById(server.getId()));
            } else {
                throw new Exception("输入服务id有错误!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    //删除产品信息
    @RequestMapping(value = "/trade/del_prod_by_id", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse delProdById(@RequestBody RdelProduct product) {
        try {
            if (checkProduct(product.getProductId())) {
                itProductService.delete(product.getProductId());
                return buildSuccess();
            } else {
                throw new Exception("产品id输入的有错误!");
            }
        } catch (Exception e) {
            ObjectOutput.exceptionOut(e, logger);
            return buildFailed().addObject("exception", e.getMessage());
        }
    }
}
