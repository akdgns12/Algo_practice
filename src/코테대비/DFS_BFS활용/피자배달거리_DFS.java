package 코테대비.DFS_BFS활용;

import java.util.ArrayList;
import java.util.Scanner;

public class 피자배달거리_DFS {
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int n,m,len;
    static int[] combi; //조합저장할 배열
    static ArrayList<Node> home, pizza;
    static int answer = Integer.MAX_VALUE;

    public static void dfs(int depth, int start){
        if(depth == m){ //하나의 조합이 완성되면
            int sum = 0; //해당 조합의 경우에서 도시의 피자배달 거리
            for (Node h : home){
                int dist = Integer.MAX_VALUE;
                for (int idx : combi){ //idx - 해당 조합의 피자집 인덱스
                    dist = Math.min(dist, Math.abs(h.x - pizza.get(idx).x) + Math.abs(h.y - pizza.get(idx).y));
                }
                sum += dist;
            }
            answer = Math.min(answer, sum);
        }
        else{
            for (int i = start; i < len; i++) {
                combi[depth] = i;
                dfs(depth+1, i+1);
            }
        }
    }

    public static void main(String[] args) {
        피자배달거리_DFS T = new 피자배달거리_DFS();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        home = new ArrayList<>();
        pizza = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int tmp = sc.nextInt();
                if(tmp == 1) home.add(new Node(i,j));
                else if(tmp == 2) pizza.add(new Node(i,j));
            }
        }
        len = pizza.size();
        combi = new int[m]; // len C m -> 피자집 개수 중에서 m개를 뽑아야 한다
        T.dfs(0,0);
        System.out.println(answer);
    }
}
