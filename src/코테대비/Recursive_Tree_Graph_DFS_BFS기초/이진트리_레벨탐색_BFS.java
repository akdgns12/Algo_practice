package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

import java.util.LinkedList;
import java.util.Queue;

public class 이진트리_레벨탐색_BFS {
    static class Node{
        int data;
        Node lt, rt;
        public Node(int val){
            data = val;
            lt = rt = null;
        }
    }

    Node root;
    static void BFS(Node root){
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        while(!q.isEmpty()){
            int len = q.size();
            System.out.print(level + "level : ");
            for (int i = 0; i < len; i++) {
                Node cur = q.poll();
                System.out.print(cur.data + " ");
                if(cur.lt != null) q.offer(cur.lt);
                if(cur.rt != null) q.offer(cur.rt);
            }
            level++;
            System.out.println();
        }
    }

    public static void main(String[] args) {
        이진트리_레벨탐색_BFS tree = new 이진트리_레벨탐색_BFS();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        tree.root.rt.lt = new Node(6);
        tree.root.rt.rt = new Node(7);
        tree.BFS(tree.root);
    }
}
