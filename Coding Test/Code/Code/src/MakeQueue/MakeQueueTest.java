package MakeQueue;

public class MakeQueueTest {
    public static void main(String[] args) {
        MakeQueue<Integer> queue = new MakeQueue<Integer>();

        queue.enqueue(1);
        queue.enqueue(2);

        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

}
