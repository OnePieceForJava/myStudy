package my.study.chapter7.classloading;

public class Test1 {

    static class SurperClass{
        static {
            System.out.println("SurperClass init...");
        }

        public static int sunper_value = 123;
    }

    static class SubClass extends  SurperClass{
        static {
            System.out.println("SubClass init...");
        }
    }

    public static void main(String[] args) {
       //test1();
        //test2();
        test3();
    }


    private  static void test1(){
        System.out.println(SubClass.sunper_value);
    }


    private  static void test2(){
        SubClass[] subClasses = new SubClass[10];
    }


    static class ConstClass{
        static {
            System.out.println("ConstClass init...");
        }
        //注意，这里有没有final关键字很重要，直接影响test3()方法测试结果
        public static final String HELLO_WORLD = "hello world";
    }

    private  static void test3(){
        System.out.println(ConstClass.HELLO_WORLD);
    }

}
