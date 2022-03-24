package org.lili.mockito;

/**
 * @author lili
 * @date 2022/3/22 16:12
 */
public class Student {

    private PersonPrinter personPrinter;

    /**
     * 方法入参和返回值都是空的测试
     */
    public void realStudent() {
        HighSchoolStudent highSchoolStudent = new HighSchoolStudent();
        highSchoolStudent.setName("highSchoolStudent");
        highSchoolStudent.setAge(10);
        highSchoolStudent.setId(123456789);
        highSchoolStudent.setGrade(3);
        //verify highSchoolStudent
        personPrinter.printPerson(highSchoolStudent);
    }

    public void setPersonPrinter(PersonPrinter personPrinter) {
        this.personPrinter = personPrinter;
    }
}
