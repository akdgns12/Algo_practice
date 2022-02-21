package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

/**
 *  1 - 7 까지의 이진트리
 *          1
 *      2       3
 *   4    5  6    7
 */
/*
    1. 전위순회 (1, 2, 4, 5, 3, 6, 7)
     - 부모, 왼쪽자식, 오른쪽자식 순서v
    2. 중위순회 (4, 2, 5, 1, 6, 3, 7)
     - 왼쪽자식, 부모, 오른쪽자식 순서
    3. 후위순회 (4, 5, 2, 6, 7, 3, 1)
     - 왼쪽자식, 오른쪽자식, 부모 순
 */
public class 이진트리순회_DFS { // 깊이우선탐
    static class Node{
        int data;
        Node lt, rt; // Node라는 클래의 객체 주소를 저장하는 변수
        public Node(int val){
            data = val;
            lt = rt = null;
        }
    }

    Node root;
    public void DFS(Node root) {
        if(root == null) return;
        else{
//            System.out.print(root.data + " "); 전위순회
            DFS(root.lt);
//            System.out.print(root.data + " "); 중위순회
            DFS(root.rt);
//            System.out.print(root.data + " "); 후위순회
        }
    }

    public static void main(String[] args) {
        이진트리순회_DFS tree = new 이진트리순회_DFS();
        tree.root = new Node(1);
        tree.root = new Node(2);
        tree.root = new Node(3);
        tree.root = new Node(4);
        tree.root = new Node(5);
        tree.root = new Node(6);
        tree.root = new Node(7);
        tree.DFS(tree.root);
    }
}
