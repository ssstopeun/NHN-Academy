package com.nhnacademy.init;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@Slf4j
@HandlesTypes(
        value = {
                com.nhnacademy.Controller.Command.class
        }
)
public class WebAppInitializer implements ServletContainerInitializer {
    @SneakyThrows
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        ControllerFactory controllerFactory = new ControllerFactory();
        controllerFactory.init(c);
        ctx.setAttribute("controllerFactory", controllerFactory);

    }

}