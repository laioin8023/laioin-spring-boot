package com.laioin.boot.web.controller;

import com.laioin.boot.common.modle.ResultMsg;
import com.laioin.boot.common.pagination.Pagination;
import com.laioin.boot.server.entry.User;
import com.laioin.boot.server.service.IUserService;
import com.laioin.boot.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    /**
     * 查找用户
     *
     * @param name
     * @return
     */
    @RequestMapping("/findUser")
    public ResultMsg findUser(String name) {
        Map<String, Object> user = userService.findUser();
        user.put("name", name);
        return buildSuccess(user);
    }

    /**
     * 查找用户，根据用户id
     *
     * @param userId
     * @return
     */
    @RequestMapping("/query/{userId}")
    public ResultMsg<User> query(@PathVariable("userId") Integer userId) {
        User user = userService.findById(userId);
        return buildSuccess(user);
    }

    /**
     * 分页查询用户
     *
     * @param pageIndex 起始页码
     * @param pageSize  页码大小
     * @return
     */
    @RequestMapping("/query/list/{pageIndex}/{pageSize}")
    public ResultMsg queryList(@PathVariable("pageIndex") Integer pageIndex,
                               @PathVariable("pageSize") Integer pageSize) {
        Pagination<List<User>> pages = userService.findPage(pageIndex, pageSize);
        return buildSuccess(pages);
    }

    /**
     * 分页查询用户,根据性别
     *
     * @param pageIndex 起始页码
     * @param pageSize  页码大小
     * @return
     */
    @RequestMapping("/query/list/{sex}/{pageIndex}/{pageSize}")
    public ResultMsg queryList(@PathVariable("sex") Integer sex,
                               @PathVariable("pageIndex") Integer pageIndex,
                               @PathVariable("pageSize") Integer pageSize) {
        Pagination<List<User>> pages = userService.findPageBySex(sex, pageIndex, pageSize);
        return buildSuccess(pages);
    }
}
