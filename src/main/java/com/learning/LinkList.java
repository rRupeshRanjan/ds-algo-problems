package com.learning;

import com.learning.DataStructures.ListNode;

import java.util.List;

public class LinkList {
    private static ListNode newHead;

    public static void main(String[] args) {
        LinkList linkList = new LinkList();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode listNode = linkList.removeElements(node1, 1);
        System.out.println(listNode);
    }

    public ListNode reverseList(ListNode head) {
        if(head!=null)
            recurse(head, head.next);
        return newHead;
    }

    public void recurse(ListNode curr, ListNode future){
        if(future==null) {
            newHead = curr;
            return;
        } else {
            recurse(future, future.next);
            future.next = curr;
            curr.next = null;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        if(head==null) return null;

        ListNode node = new ListNode(-1);
        ListNode answer = node;
        while(head!=null) {
            if(head.val != val) {
                node.next = new ListNode(head.val);
                node = node.next;
            }
            head = head.next;
        }

        return answer.next;
    }
}
