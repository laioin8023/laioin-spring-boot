package com.laioin.boot.server.service.impl;

import com.laioin.boot.common.pagination.Pagination;
import com.laioin.boot.server.entry.User;
import com.laioin.boot.server.mapper.UserMapper;
import com.laioin.boot.server.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 查找用户
     *
     * @return
     */
    @Override
    public Map<String, Object> findUser() {
        Map<String, Object> user = new TreeMap<>();
        user.put("id", 1);
        user.put("phone", "13161781665");
        user.put("sex", 1);
        user.put("nickname", "大姐夫");
        return user;
    }

    /**
     * 根据用户id，查找用户
     *
     * @param id
     * @return
     */
    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * 分页查询用户
     *
     * @param pageIndex 开始页码
     * @param pageSize  每页多大
     * @return
     */
    @Override
    public Pagination<List<User>> findPage(Integer pageIndex, Integer pageSize) {
        Pagination<List<User>> pagination = new Pagination<>(pageIndex, pageSize);
        // 分页查询
        List<User> list = userMapper.selectAllUser(pagination);
        pagination.setResultList(list);
        return pagination;
    }

    /**
     * 根据性别查询用户
     *
     * @param sex       性别
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public Pagination<List<User>> findPageBySex(Integer sex, Integer pageIndex, Integer pageSize) {
        Pagination<List<User>> pagination = new Pagination<>(pageIndex, pageSize);
        // 分页查询
        List<User> list = userMapper.selectBySex(sex, pagination);
        pagination.setResultList(list);
        return pagination;
    }
}
