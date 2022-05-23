package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ나무재테크 {
    static int n,m,k;
    static int[][] map, add;
    static int[] dx = {-1,-1,-1,0,1,1,1,0};
    static int[] dy = {-1,0,1,1,1,0,-1,-1};
    static ArrayList<Tree> tree = new ArrayList<>();
    static ArrayList<Tree> liveTree;
    static ArrayList<Tree> deadTree;

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
            int age = Integer.parseInt(st.nextToken());

            tree.add(new Tree(x, y, age));
        }

        while (k > 0) {
            liveTree = new ArrayList<>();
            deadTree = new ArrayList<>();

            spring();
            summer();
            fall();
            winter();
            k--;
        }

        System.out.println(tree.size());
    }

    // 나무가 자신의 나이만큼 양분을 먹고, 나이가 1증가
    static void spring(){
        for (int i=0; i<tree.size(); i++){
            Tree node = tree.get(i);
            if (map[node.x][node.y] < node.age) {
                deadTree.add(node);
            } else {
                map[node.x][node.y] += node.age;
                node.age += 1;
                liveTree.add(node);
            }
        }
        tree.clear();
        tree.addAll(liveTree);
    }

    // 봄에 죽은 나무가 양분으로 변한다. 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가
    static void summer() {
        for (int i = 0; i < deadTree.size(); i++) {
            Tree node = deadTree.get(i);
            map[node.x][node.y] += node.age / 2;
        }
    }

    // 나무가 번식. 나무의 나이가 5의 배수라면 인접한 8개의 칸에 나이가 1인 나무가 생김
    static void fall() {
        for (int i = 0; i < liveTree.size(); i++) {
            Tree node = liveTree.get(i);
            if (node.age % 5 == 0) {
                for (int j = 0; j < 8; j++) {
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];

                    if(nx >= 0 && ny >= 0 && nx < n && ny < n){
                        tree.add(new Tree(nx, ny, 1));
                    }
                }
            }
        }
    }

    // 땅에 양분 추가
    static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] += add[i][j];
            }
        }
    }

    static class Tree implements Comparable<Tree>{
        int x, y;
        int age;

        public Tree(int x, int y, int age){
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o){
            return this.age - o.age;
        }
    }
}
