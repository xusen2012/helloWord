package com.links86.trade.controller;

import com.links86.trade.domain.TUser;
import com.links86.trade.domain.out.RespUser;
import com.links86.trade.domain.to.CResponse;
import com.links86.trade.domain.to.RsaveUser;
import com.links86.trade.exception2.Links86Exception;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * 用户同步接口x
 * Created by leoxu on 2016/11/11.
 */
@Controller
public class UserController extends BaseController {

    @RequestMapping(value = "/trade/save_user_snap", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse saveUserSnap(@RequestBody RsaveUser rsaveUser) {
        try {
            TUser tUser = new TUser();
            if (rsaveUser.getId() == 0) {
                tUser.setId(snowflake.next());
                if (checkAccountType(rsaveUser.getType().getCode()))
                    tUser.setType(rsaveUser.getType());
                tUser.setName(rsaveUser.getName());
                tUser.setUpdateTime(new Date());
                tUser.setOutUid(rsaveUser.getOutUId());
                itUserService.save(tUser);
                return buildSuccess().addObject("data", "新增成功").addObject("id", "" + tUser.getId());
            } else if (!itUserService.exists(rsaveUser.getId())) {
                throw new Exception("用户id不存在!");
            } else {
                tUser.setId(rsaveUser.getId());
                if (checkAccountType(rsaveUser.getType().getCode()))
                    tUser.setType(rsaveUser.getType());
                tUser.setName(rsaveUser.getName());
                tUser.setUpdateTime(new Date());
                tUser.setOutUid(rsaveUser.getOutUId());
                itUserService.save(tUser);
                return buildSuccess().addObject("data", "更新成功");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }


    @RequestMapping(value = "/trade/find_user_snap", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse findUserSnap(@RequestBody RsaveUser rsaveUser) {
        try {
            long id = rsaveUser.getId();
            if (checkUser(id)) {
                TUser user = itUserService.findById(id);

                //小试一下kotlin,后来又取消了
                RespUser respUser =
                        new RespUser(String.valueOf(user.getId()),
                                user.getName(),
                                String.valueOf(user.gettAccount().getId()),
                                user.gettAccount().getName(),
                                user.getType().getCode(),
                                user.getUpdateTime());

                return buildSuccess().addObject("data", respUser);
            } else {
                throw new Exception("输入的用户id有错误!");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    /**
     * 通过注册的外部ouid返回tuser对象
     * @param rsaveUser 传入 outuid即可
     * @return  返回user 信息
     */
    @RequestMapping(value = "/trade/find_user_by_ouid", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse findUserByOUID(@RequestBody RsaveUser rsaveUser) {
        try {
            long ouid = rsaveUser.getOutUId();
            if (ouid != 0) {
                TUser user = itUserService.findByOUID(ouid);
                RespUser respUser =
                        new RespUser(String.valueOf(user.getId()),
                                user.getName(),
                                String.valueOf(user.gettAccount().getId()),
                                user.gettAccount().getName(),
                                user.getType().getCode(),
                                user.getUpdateTime());
                return buildSuccess().addObject("data",respUser);
            } else {
                throw new Links86Exception("外部用户id输入有错误");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }


    @RequestMapping(value = "/trade/del_user_snap", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse delUserSnap(@RequestBody RsaveUser rsaveUser) {
        try {
            if (checkUser(rsaveUser.getId())) {
                itUserService.delete(itUserService.findById(rsaveUser.getId()));
                return buildSuccess().addObject("data", "删除成功!");
            } else {
                throw new Exception("用户信息不存在!");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

    @RequestMapping(value = "/trade/list_user_snap", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public CResponse listUserSnap(@RequestBody RsaveUser rsaveUser) {
        try {
            int pageIndex = rsaveUser.getPageIndex();
            int pageSize = rsaveUser.getPageSize();
            String direction = rsaveUser.getDirection();
            String[] orderFields = rsaveUser.getOrderFields();
            setPageRequest(pageIndex, pageSize, direction, orderFields);
            return buildSuccess().addObject("result", itUserService.findAll(pageRequest));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return buildFailed().addObject("exception", e.getMessage());
        }
    }

}
