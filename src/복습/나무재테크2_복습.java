package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 나무재테크2_복습 {
    static int n, m, k;
    static int[][] map, add;
    static int[] dx = {-1,-1,-1,0,1,1,1,0}; // 좌상,상,우상,우,우하,하,좌하,좌
    static int[] dy = {-1,0,1,1,1,0,-1,-1};
    static ArrayList<Node> tree = new ArrayList<>();
    static ArrayList<Node> deadTree;
    static ArrayList<Node> liveTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        add = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = 5;
                add[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            tree.add(new Node(x,y,z));
        }

        while (k > 0) {
            deadTree = new ArrayList<>();
            liveTree = new ArrayList<>();

            Collections.sort(tree);

            spring();
            summer();
            fall();
            winter();
            k--;
        }

        System.out.println(tree.size());
    }

    static void spring() {
        for (int i = 0; i < tree.size(); i++) {
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

    static void summer() {
        for (int i = 0; i < deadTree.size(); i++) {
            Node node = deadTree.get(i);

            map[node.x][node.y] += node.age / 2;
        }
    }

    static void fall() {
        for (int i = 0; i < tree.size(); i++) {
            Node node = tree.get(i);

            if(node.age % 5 == 0){
                for (int j = 0; j < 8; j++) {
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];

                    if(nx >= 0 && ny >= 0 && nx < n && ny < n){
                        tree.add(new Node(nx,ny,1));
                    }
                }
            }
        }
    }

    static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] += add[i][j];
            }
        }
    }

    static class Node implements Comparable<Node>{
        int x, y;
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
}
