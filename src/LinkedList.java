import java.util.Iterator;
import java.util.function.Consumer;

public class LinkedList {


    private class Node {
        private int element;
        private Node next;
        private Node prev;

        public Node(int element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addFirst(int element) {
        if (this.head == null) {
            addLast(element);
        } else {
            Node newNode = new Node(element);
            this.head.prev = newNode;
            newNode.next = this.head;
            this.head = newNode;
            this.size++;
        }
    }

    public void addLast(int element) {
        Node newNode = new Node(element);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.prev = this.tail;
            this.tail.next = newNode;
            this.tail = newNode;
            if (this.head.next == null) {
                this.head.next = newNode;
            }
        }

        this.size++;
    }

    public int size() {
        return this.size;
    }

    public void forEach(Consumer<Integer> consumer) {
        if (this.size == 1) {
            System.out.println(this.head.element);
        } else {
            Node currentNode = this.head;
            while (currentNode != null) {
                consumer.accept(currentNode.element);
                currentNode = currentNode.next;
            }
        }
    }

    public Integer removeFirst() {
        if (this.head != null) {
            int element = this.head.element;
            this.head = this.head.next;
            if (this.head != null) {
                this.head.prev = null;
            } else {
                this.tail = null;
            }
            this.size--;
            return element;
        }
        return null;
    }

    public Integer removeAt(int count) {
        validateIndex(count);


        Node currentNode;
        int middle = this.size / 2;
        if (count > middle) {
            int end = this.size - 1;
            currentNode = this.tail;
            while (end > count) {
                currentNode = currentNode.prev;
                end--;
            }
        } else {
            int begin = 0;
            currentNode = this.head;
            while (begin < count) {
                currentNode = currentNode.next;
                begin++;
            }
        }
        int element = currentNode.element;
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        this.size--;
        return element;
    }

    public Integer removeLast() {
        if (this.tail != null) {
            int element = this.tail.element;
            this.tail = this.tail.prev;
            if (this.tail != null) {
                this.tail.next = null;
            } else {
                this.head = null;
            }
            this.size--;
            return element;
        }
        return null;
    }

    private void validateIndex(int count) {
        if (count < 0 || count >= this.size) {
            throw new IndexOutOfBoundsException(count + " Invalid Index! For size of " + this.size);
        }
    }

    public int get(int count) {
        validateIndex(count);
        Node currentNode = this.head;
        int begin = 0;
        while (begin < count) {
            currentNode = currentNode.next;
            begin++;
        }
        return currentNode.element;
    }

    public int[] toArray() {
        int[] elements = new int[this.size];
        int index = 0;
        Node currentNode = this.head;
        while (currentNode != null) {
            elements[index++] = currentNode.element;
            currentNode = currentNode.next;
        }

        return elements;
    }
}
