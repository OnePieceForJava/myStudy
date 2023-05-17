package redis;

public class Test {

    public static void main(String[] args) {
        String md = "set  hello   world   ";
        md = md.replaceAll("\\s{1,}"," ");
        System.out.println( md.split(" ").length);

    }
}
