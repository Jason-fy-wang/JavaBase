package com.wk.interview;

import java.util.Arrays;
import java.util.List;

public class LinkList {

    public Node<String> createList() {
        Node<String> head = new Node<>();
        head.setValue("head");
        Node<String> iterator = head;
        List<String> list = Arrays.asList("one", "two", "three");
        for (String s : list) {
            Node<String> temp = new Node<>();
            temp.setValue(s);
            iterator.next = temp;
            iterator = temp;
        }
        return head;
    }

    public void printList(Node<String> head) {
        while (head!=null && head.notEmpty()) {
            System.out.println(head.getValue());
            head = head.next;
        }
    }


    public static void main(String[] args) {
        LinkList list = new LinkList();
        Node<String> node = list.createList();
        list.printList(node);
    }
}
