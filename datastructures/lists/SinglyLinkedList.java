package datastructures.lists;

public class SinglyLinkedList<T> {

    private Node<T> head;
    private int size;

    // Inserts data at the end (tail)
    public void insert(T data) {
        Node<T> node = new Node<>(data);
        if (this.head == null) {
            this.head = node;
        } else {
            Node<T> last = this.head;
            while (last.getNextNode() != null) {
                last = last.getNextNode();
            }
            last.setNextNode(node);
        }
        size++;
    }

    // Inserts data at the start (head)
    public void insertAtHead(T data) {
        Node<T> node = new Node<>(data);
        node.setNextNode(head);
        head = node;
        size++;
    }

    // Inserts data at a specific index
    public void insertAtIndex(T data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            insertAtHead(data);
            return;
        }

        Node<T> node = new Node<>(data);
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNextNode();
        }
        node.setNextNode(current.getNextNode());
        current.setNextNode(node);
        size++;
    }

    // Deletes data at a specific index
    public T deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> current = head;
        if (index == 0) {
            head = head.getNextNode();
        } else {
            Node<T> previous = null;
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.getNextNode();
            }
            previous.setNextNode(current.getNextNode());
        }
        size--;
        return current.getData();
    }

    // Retrieves the size of the linked list
    public int getSize() {
        return size;
    }

    // Retrieves data from a specific index
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNextNode();
        }
        return current.getData();
    }

    // Prints the list data
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.getData() + " -> ");
            current = current.getNextNode();
        }
        System.out.println("null");
    }

    // Internal Node class
    private static class Node<T> {
        private T data;
        private Node<T> nextNode;

        public Node(T data) {
            this.data = data;
            this.nextNode = null;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }
    }

    // Getter for the head node
    public Node<T> getHead() {
        return head;
    }

    // Setter for the head node
    public void setHead(Node<T> head) {
        this.head = head;
    }
}
