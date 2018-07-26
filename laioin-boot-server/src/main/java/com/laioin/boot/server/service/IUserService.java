package com.laioin.boot.server.service;

import com.laioin.boot.common.pagination.Pagination;
import com.laioin.boot.server.entry.User;

import java.util.List;
import java.util.Map;

/**
 * 用户服务类
 */
public interface IUserService {

    /**
     * 查找用户
     *
     * @return
     */
    Map<String, Object> findUser();

    /**
     * 根据用户id，查找用户
     *
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 分页查询用户
     *
     * @param pageIndex 开始页码
     * @param pageSize  每页多大
     * @return
     */
    Pagination<List<User>> findPage(Integer pageIndex, Integer pageSize);

    /**
     * 根据性别查询用户
     *
     * @param sex       性别
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Pagination<List<User>> findPageBySex(Integer sex, Integer pageIndex, Integer pageSize);
}

