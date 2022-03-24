package org.lili.mockito;

/**
 * @author lili
 * @date 2022/3/22 16:06
 */
public class HighSchoolStudent implements Person {
    private String name;
    private int age;
    private int id;
    private int grade;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }
}