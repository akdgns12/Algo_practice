package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 말단노드까찌의가장짧은경로_BFS {
    static class Node{
        int data;
        Node lt, rt;
        public Node(int val){
            data = val;
            lt = rt = null;
        }
    }

    Node root;
    static int bfs(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int level = 0;
        while(!q.isEmpty()){
            int len = q.size(); // 해당 level의 길이
            for (int i = 0; i < len; i++) { //그 level에 있는 노드들을 뿌려준다
                Node cur = q.poll();
                if(cur.lt == null && cur.rt == null) return level;
                if(cur.lt != null) q.offer(cur.lt);
                if(cur.rt != null) q.offer(cur.rt);
            }
            level++;
        }
        return 0;
    }

    public static void main(String[] args) {
        말단노드까찌의가장짧은경로_BFS tree = new 말단노드까찌의가장짧은경로_BFS();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        System.out.println(tree.bfs(tree.root));
    }
}
