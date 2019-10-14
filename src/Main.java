import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedList linkedList = new LinkedList();

        linkedList.addFirst(13);
        linkedList.removeFirst();

        linkedList.forEach(System.out::println);
    }
}
