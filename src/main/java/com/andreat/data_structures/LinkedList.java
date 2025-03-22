package com.andreat.data_structures;

public class LinkedList<T> {

    static class ListNode<T>{
        T data;
        ListNode<T> next;

        public ListNode(T data){
            this.data = data;
            this.next = null;
        }
    }

    private ListNode<T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void add(T data){
        ListNode<T> newNode = new ListNode<>(data);
        if(head == null){
            head = newNode;
        } else{
            ListNode<T> current = head;
            while(current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public void addFirst(T data){
        ListNode<T> newNode = new ListNode<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public boolean remove(T data){
        if(head == null) return false;

        if(head.data.equals(data)){
            head = head.next;
            size--;
            return true;
        }

        ListNode<T> current = head;
        while(current.next != null && !current.next.data.equals(data)){
            current = current.next;
        }

        if(current.next != null){
            current.next = current.next.next;
            size--;
            return true;
        }

        return false;
    }

    public boolean contains(T data){
        ListNode<T> current = head;
        while(current != null){
            if(current.data.equals(data)){
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode<T> current = head;

        sb.append("[");
        while(current != null){
            sb.append(current.data);
            if(current.next != null){
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");

        return sb.toString();
    }
}