package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 나무재테크 {
    static class Node implements Comparable<Node>{
        int x,y;
        int age;
        public Node(int x, int y, int age){
            this.x = x;
            this.y = y;
            this.age = age;
        }
        @Override
        public int compareTo(Node o){
            return this.age - o.age;
        }
    }
    static int N,M,K;
    static int[][] map;
    static int[][] add;
    static ArrayList<Node> tree = new ArrayList<>();
    static ArrayList<Node> liveTree;
    static ArrayList<Node> deadTree;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        add = new int[N][N]; // 추가되는 양분

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = 5;
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());

            tree.add(new Node(x, y, age));
        }

        while(K > 0){
            liveTree = new ArrayList<>();
            deadTree = new ArrayList<>();
            spring();
            summer();
            fall();
            winter();
            K--;
        }

        System.out.println(tree.size());
    }

    static void spring(){
        for(int i=0; i<tree.size(); i++){
            Node node = tree.get(i);
            if(node.age > map[node.x][node.y]){
                deadTree.add(node);
            }else{
                map[node.x][node.y] -= node.age;
                node.age += 1;
                liveTree.add(node);
            }
        }
        tree.clear();
        tree.addAll(liveTree);
    }

    static void summer(){
        for(int i=0; i<deadTree.size(); i++){
            Node node = deadTree.get(i);
            map[node.x][node.y] += node.age/2;
        }
    }

    static void fall(){
        for(int i=0; i<tree.size(); i++){
            Node node = tree.get(i);
            if(node.age % 5 == 0){
                for(int j=0; j<8; j++){
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];

                    if(nx >= 0 && ny >= 0 && nx < N && ny < N){
                        tree.add(new Node(nx, ny, 1));
                    }
                }
            }
        }
    }

    static void winter(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] += add[i][j];
            }
        }
    }
}
