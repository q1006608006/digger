package mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.ivan.domain.TestUserData;
import top.ivan.service.TestUserService;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * description
 *
 * @author Administrator
 * @date 2017/8/18
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})
public class TestUserDao {

    private static SqlSessionFactory sqlSessionFactory;

    private ApplicationContext context;
    private TestUserService userService;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("spring/*.xml");
        userService = (TestUserService) context.getBean("userService");

    }



    @Test
    public void getUser() throws FileNotFoundException {

        System.out.println(userService.getUserById(1).getName());
    }
}
