package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        try{
            Class userClass = Class.forName(User.class.getName());
            Constructor<?> constructor = userClass.getConstructor();
            User user = (User) constructor.newInstance();
            System.out.println(user);
        }catch(ClassNotFoundException | NoSuchMethodException e){
            e.printStackTrace();
        }catch(InvocationTargetException e){
            e.printStackTrace();
        }catch(InstantiationException e){
            e.printStackTrace();
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }
    }
}