package 백준.company.구현;

import 복습.문제추천시스템_Version1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10800_컬러볼 { // 각 플레이어가 사로잡을 수 있는 모든 공들의 크기의 합
    /**
     * 시간초과 : 구간합 알고리즘으로 해결가능
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 공 개수

        Node[] ball = new Node[N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            ball[i] = new Node(i, color, size);
        }
        Arrays.sort(ball, (o1, o2) -> o1.size - o2.size); // size순 정렬

        int score[] = new int[N]; // color i의 누적합
        int color[] = new int[N+1];
        int ball_idx = 0;
        int total = 0;
        for(int i=0; i<N; i++){
            Node now = ball[i];
            while(now.size > ball[ball_idx].size){
                total += ball[ball_idx].size;
                color[ball[ball_idx].color] += ball[ball_idx].size;
                ball_idx++;
            }
            System.out.println("i : " + i + " " + "ball_idx : " + ball_idx + "now.size : " + now.size + " " + "total : " + total);
            score[now.idx] = total - color[now.color];
        }

    /*
                   4
        idx : 1    1 10
        idx : 2    3 15
        idx : 3    1 3
        idx : 4    4 8

        after sort by size
        idx : 3 1 3
        idx : 4 4 8
        idx : 1 1 10
        idx : 2 3 15
        색이 다르고 사이즈가 작은 것만 더할 수 있다
        현재 기준이 되는 공의 사이즈보다 작다면 누적해서 더해주고
     */

        for(int i=0; i<N; i++) System.out.println(score[i]);
    }

    static class Node {
        int idx;
        int color;
        int size;

        public Node(int idx, int color, int size){
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }
}
