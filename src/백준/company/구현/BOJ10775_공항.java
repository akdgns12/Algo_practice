package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10775_공항 {
    static int[] parent;
    static int G, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine()); // 게이트 수
        P = Integer.parseInt(br.readLine()); // 비행기 수

        parent = new int[G+1];
        for(int i=1; i<=G; i++){
            parent[i] = i;
        }

         /*
            P개의 비행기가 주어지는데, 비행기 수 범위는 (1 ~ gi)
            gi에 바로 배치해주는게 최선임. 이미 배치되어 있다면 gi-1, gi-2 ... 1 계속해서 탐색
            탐색과정에서 union-find 사용
            -> gi-1, gi-2, gi-3 ... 배치 여부를 union-find로 통합해놓고
            그 정보로 끝까지 탐색하지 않고 판단할 수 있게
         */
        int cnt = 0;
        for(int i=0; i<P; i++){
            int plane = Integer.parseInt(br.readLine());
            int emptySpot = find(plane);

            if(emptySpot == 0){
                break;
            }

            cnt++;
            union(emptySpot, emptySpot - 1);
        }


        System.out.println(cnt);
    }

    static int find(int x){
        if(x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);
        if(x < y){
            parent[y] = x;
        }else{
            parent[x] = y;
        }
    }
}
