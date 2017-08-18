package top.ivan.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import top.ivan.domain.TestUserData;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/18
 */
public class TestUserDaoImpl implements TestUserDao {

    private SqlSessionFactory sqlSessionFactory;

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public TestUserData getUserDataById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.selectOne("namespace.selectUserById",id);
    }
}
