package 백준.company.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ16235 {
    // 나무재테크 / 골드 4 / 완탐, 구현
    static class Node implements Comparable<Node>{
        int x,y;
        int age;
        public Node(int x, int y, int age){
            this.x =x;
            this.y = y;
            this.age = age;
        }
        // 나이 오름차순으로 정렬
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
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        add = new int[N][N];
        // 양분정보
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = 5; // 초기 양분 5로 세팅
                add[i][j] = Integer.parseInt(st.nextToken()); // 추가되는 양분
            }
        }

        // 상도가 심은 나무 정보 리스트에 담기(위치, 나이)
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());

            tree.add(new Node(x,y,age));
        }

        while(K > 0){
            // 구분 초기화
            liveTree = new ArrayList<>();
            deadTree = new ArrayList<>();
            // 정렬
            Collections.sort(tree);
            spring();
            summer();
            fall();
            winter();
            K--;
        }
        System.out.println(tree.size());
    }
    // 봄
    static void spring(){
       for(int i=0; i<tree.size(); i++){
           Node node = tree.get(i);
           if(node.age > map[node.x][node.y]){ // 자신의 나이만큼 먹을 수 있는 양분이 없을때
               deadTree.add(node); // 죽은 나무 리스트에 추가
           }else{ // 양분을 먹을 수 있을 때
               map[node.x][node.y] -= node.age; // 자신의 나이만큼 양분 추가
               node.age += 1; // 나이 1증가
               liveTree.add(node); // 살아있는 나무 리스트에 추가
           }
       }
       // 나무 리스트 리셋 후 살아있는 나무로 초기화
        tree.clear();
        tree.addAll(liveTree);
    }

    // 여름
    static void summer(){
        // 봄에 죽은 나무가 양분으로 변함
        for(int i=0; i<deadTree.size(); i++){
            Node node = deadTree.get(i);
            map[node.x][node.y] += node.age/2; // 각각의 죽은 나무마다 나이를 2로 나눈값이 나무가 있던 칸에 양분으로 추가
        }
    }

    // 가을
    static void fall() {
        for (int i = 0; i < tree.size(); i++) {
            Node node = tree.get(i);
            if (node.age % 5 == 0) { // 나무의 나이가 5의 배수라면 번식
                for (int j = 0; j < 8; j++) {
                    int nx = node.x + dx[j];
                    int ny = node.y + dy[j];

                    if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                        tree.add(new Node(nx, ny, 1));
                    }
                }
            }
        }
    }

        // 겨울
        static void winter () {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] += add[i][j];
                }
            }
        }
    }
