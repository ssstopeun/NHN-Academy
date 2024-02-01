package com.nhnacademy.init;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class ControllerFactory {
    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();

    public void init(Set<Class<?>> c) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //todo beanMap에 key = method + servletPath, value = Controller instance
        for (Class<?> s : c) {
            RequestMapping req = s.getAnnotation(RequestMapping.class);
            beanMap.put(req.method() + req.value(), s.getDeclaredConstructor().newInstance());
        }
    }

    public Object getBean(String method, String path) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //todo beanMap 에서 method+servletPath을 key로 이용하여 Controller instance를 반환합니다.
        return beanMap.get(method + path);
    }
}
//@Slf4j
//public class ControllerFactory {
//
//    private final ConcurrentMap<String, Object> beanMap = new ConcurrentHashMap<>();
//
//    public void init(Set<Class<?>> c) {
//        for (Class<?> clazz : c) {
//            log.info("controller-class: {}", clazz.getName());
//            Annotation[] annotations = clazz.getAnnotations();
//            if (annotations.length > 0) {
//                String annotation = annotations[0].toString();
//                log.info("annotation: {}", annotation);
//
//                if (isController(annotation)) {
//                    String method = getMethod(annotation);
//                    String path = getPath(annotation);
//                    String key = getKey(method, path);
//
//                    log.info("method: {}", method);
//                    log.info("path: {}", path);
//                    log.info("key: {}", key);
//
//                    try {
//                        Object command = clazz.getDeclaredConstructor().newInstance();
//                        beanMap.put(key, command);
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }
//    }
//
//    public Object getBean(String method, String path) {
//        String key = getKey(method, path);
//        if (!beanMap.containsKey(key)) {
//            throw new RuntimeException("controller Not Found");
//        }
//        return beanMap.get(key);
//    }
//
//    private String getKey(String method, String path) {
//        //GET-/student/list.do
//        return method + "-" + path;
//    }
//
//    private String getPath(String annotation) {
//        int start = annotation.indexOf("value=") + 7;
//        int end = annotation.length() - 2;
//        return annotation.substring(start, end);
//    }
//
//    private boolean isController(String annotations) {
//        return annotations.contains("RequestMapping");
//    }
//
//    private String getMethod(String annotation) {
//        int start = annotation.indexOf("method=") + 7;
//        int end = annotation.indexOf(",");
//        return annotation.substring(start, end);
//    }
//}