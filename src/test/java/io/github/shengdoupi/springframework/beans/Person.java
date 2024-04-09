package io.github.shengdoupi.springframework.beans;

/**
 * @author zhoukehh
 * @date 2024/3/21
 * @description
 */
public class Person {
    
    private String name;
    
    public Person() {
    }
    
    public Person(String name) {
        this.name = name;
    }
    
    public void sayHello() {
        if (null == name) {
            System.out.println("Hello world");
        } else {
            System.out.println("Hello " + name);
        }
    }
}
