package lili.mock;

import org.easymock.EasyMock;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * @author lili
 * @date 2020/7/1 15:05
 * @description
 */
class User {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

public class MockUser {
    public static void main(String[] args) {

        PodamFactory factory = new PodamFactoryImpl();
        User user = factory.manufacturePojo(User.class);
        System.out.println(user);


        User mock = EasyMock.createMock(User.class);
        System.out.println(mock.getId());
      //  System.out.println(mock.getName());
        System.out.println(mock.toString());
    }
}
