package Etc;

public class EqualVersisIdentical {
    public static void main(String[] args) {

        // Equal
        String a = "안녕.";
        String b = "안녕";
        b = b + ".";
        String c = "안녕.";

        System.out.println("a hash : " + a.hashCode());
        System.out.println("b hash : " + b.hashCode());

        System.out.println("동등성 : "+ a.equals(b));


        // Identical
        System.out.println("동일성 : " + (a == b));
        System.out.println("a와 c의 동일성 : " + (a == c));
        System.out.println("a와 c의 동등성 : " + (a.equals(c)));

        c = "안녕";
        c += ".";
        System.out.println("a와 c의 동일성 : " + (a == c));
        System.out.println("a와 c의 동등성 : " + (a.equals(c)));

    }
}
