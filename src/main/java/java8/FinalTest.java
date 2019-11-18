package java8;



/**
 * @Description
 * @auther wcc
 * @create 2019-10-17 22:44
 */
public class FinalTest {
    public static void main(String[] args) {
        final User user = new User();
        user.addree = new Addree("bbbb");
        System.out.println(user.addree.addr);

    }
}


class User{
    Addree addree = new Addree("aaaa");

    public void setAddree(Addree addree) {
        this.addree = addree;
    }
}

class Addree{
    String addr;

    public Addree(String addr) {
        this.addr = addr;
    }
}
