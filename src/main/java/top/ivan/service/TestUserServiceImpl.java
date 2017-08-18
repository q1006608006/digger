package top.ivan.service;

import org.springframework.beans.factory.annotation.Autowired;
import top.ivan.dao.TestUserDao;
import top.ivan.domain.TestUserData;
import top.ivan.mapper.TestUserMapper;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/18
 */
public class TestUserServiceImpl implements TestUserService {
    @Autowired
    private TestUserMapper userMapper;

    public TestUserData getUserById(int id) {
        return userMapper.selectUserById(id);
    }
}
