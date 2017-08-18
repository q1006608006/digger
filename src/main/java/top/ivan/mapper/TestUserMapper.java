package top.ivan.mapper;

import top.ivan.domain.TestUserData;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/18
 */
public interface TestUserMapper {
    TestUserData selectUserById(int id);
}
