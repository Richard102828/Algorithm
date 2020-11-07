public class User extends Data{
    public static int num = 12;
    public static final int nums = 13;
    public int a = 14;
    static {
        System.out.println("user static块");
    }
    public User() {
        System.out.println("User 构造方法");
    }
    {
        System.out.println("user 块");
    }
    public void getshe() {
        System.out.println("getshe");
    }

    @Override
    public void data() {
        System.out.println("user data");
    }
}

class Data {
    public static int b = 2;
    static {
        System.out.println("data static 块");
    }
    public Data() {
        System.out.println("Data 构造方法");
    }
    {
        System.out.println("data 块");
    }
    public void data() {
        System.out.println("data data");
    }

    public static void shenm() {
        System.out.println("shenm");
    }
}
class TestA {
    public static void main(String[] args) {
        User user = new User();
    }
}
