package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ16235 {
    // 나무 재테크 / 골드 4 / 구현
    /*
        초기 양분은 모든 칸에 5.
        봄,여름,가을,겨울 계절마다의 조건에 맞게 구현
     */
    static int n, m, k;
    static int[][] map, add;
    static ArrayList<Tree> tree = new ArrayList<>();
    static ArrayList<Tree> liveTree;
    static ArrayList<Tree> deadTree;
    static int[] dx = {-1,-1,-1,0,1,1,1,0};
    static int[] dy = {-1,0,1,1,1,0,-1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 나무의 개수
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        add = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = 5; // 초기양분 5
                add[i][j] = Integer.parseInt(st.nextToken()); // 추가되는 양분
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken()); // 나이

            tree.add(new Tree(x,y,z));
        }

        while(k > 0){
            liveTree = new ArrayList<>();
            deadTree = new ArrayList<>();

            // 나이가 어린 것부터 양분을 먹으므로 오름차순 정렬
            Collections.sort(tree);
            spring();
            summer();
            fall();
            winter();
            k--;
        }

        System.out.println(tree.size());
    }

    // 자신의 나이만큼 양분을 먹고, 나이가 1 증가
    static void spring(){
        for (int i = 0; i < tree.size(); i++) {
            Tree node = tree.get(i);
            if (node.age > map[node.x][node.y]) { // 자신의 나이만큼 먹을 수 있는 양분이 없을 때
                deadTree.add(node);
            } else { // 먹을 양분이 있을 때 자신의 나이만큼 양분먹고 나이 1 증가
                map[node.x][node.y] -= node.age;
                node.age += 1;
                liveTree.add(node);
            }
        }
        tree.clear();
        tree.addAll(liveTree);
    }

    // 여름에는 봄에 죽은 나무가 양분으로 변함. 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가
    static void summer(){
        for (int i = 0; i < deadTree.size(); i++) {
            Tree node = deadTree.get(i);
            map[node.x][node.y] += node.age/2;
        }
    }

    // 번식, 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
    static void fall(){
        for (int i=0; i<tree.size(); i++){
            Tree node = tree.get(i);
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

    // 땅을 돌아다니면서 양분을 추가
    static void winter(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] += add[i][j];
            }
        }
    }

    static class Tree implements Comparable<Tree>{
        int x, y;
        int age;

        public Tree(int x, int y, int age) {
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

