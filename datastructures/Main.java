package datastructures;

import datastructures.lists.SinglyLinkedList;

class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> sll = new SinglyLinkedList<>();
        sll.insert("Hello");
        sll.insert("World");
        sll.insert("This");
        sll.insert("Is");
        sll.insert("My");
        sll.insert("Java");
        sll.insert("Implementation");
        sll.insert("Of");
        sll.insert("The");
        sll.insert("Singly");
        sll.insert("Linked");
        sll.insert("List");
        sll.insert("!");


        sll.printList();
    }
}