package com.basejava.homework;

import com.basejava.homework.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        System.out.println(r);
        // TODO : invoke r.toString via reflection
        Method method = r.getClass().getDeclaredMethod("toString");
        String result = (String) method.invoke(r);
        System.out.println(result);
        String result2 = (String) method.invoke(new Resume());
        System.out.println(result2);
    }
}
