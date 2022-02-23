package 코테대비.Recursive_Tree_Graph_DFS_BFS기초;

public class 말단노드까지의가장짧은경로_DFS {
    static class Node{
        int data;
        Node lt, rt;
        public Node(int val) {
            data = val;
            lt = rt = null;
        }
    }

    Node root;
    public static int dfs(int level, Node root){
        if(root.lt == null && root.rt == null) return level; //말단노드라면 level return
        else return Math.min(dfs(level + 1, root.lt), dfs(level + 1, root.rt)); //말단노드가 아니라면 뻗어나가기
    }

    public static void main(String[] args) {
        말단노드까지의가장짧은경로_DFS tree = new 말단노드까지의가장짧은경로_DFS();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        System.out.println(tree.dfs(0, tree.root));
    }
}
