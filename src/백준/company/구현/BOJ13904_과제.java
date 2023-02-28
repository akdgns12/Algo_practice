package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13904_과제 {
    static int N, d, w;
    static List<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); // 마감일까지 남은 일수
            int w = Integer.parseInt(st.nextToken()); // 과제 점수

            list.add(new Node(d,w));
        }

    }

    static class Node{
        int d, w;
        public Node(int d, int w){
            this.d = d;
            this.w = w;
        }
    }
}
