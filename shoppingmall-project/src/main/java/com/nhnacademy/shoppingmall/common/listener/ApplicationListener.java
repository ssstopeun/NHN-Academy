package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@WebListener
@Slf4j
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 admin,user 등록합니다. 만약 존재하면 등록하지 않습니다.
        User testAdmin = new User(
                "test_admin","TEST_ADMIN","admin","00000000", User.Auth.ROLE_ADMIN,1000000, LocalDateTime.now(),null
        );
        User testUser = new User(
                "test_user","TEST_USER","test","00000000", User.Auth.ROLE_USER,1000000,LocalDateTime.now(),null
        );
        DbConnectionThreadLocal.initialize();
        if(userService.getUser("test_admin")==null){
            userService.saveUser(testAdmin);
        }
        if(userService.getUser("test_user")==null){
            userService.saveUser(testUser);
        }

        DbConnectionThreadLocal.reset();

        ServletContext context = sce.getServletContext();
        context.setAttribute("userService",userService);
    }
}
