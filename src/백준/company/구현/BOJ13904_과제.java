package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13904_과제 {
    static int N;
    static List<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        int Max = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); // 마감일까지 남은 일수
            int w = Integer.parseInt(st.nextToken()); // 점수

            list.add(new Node(d, w));
            if(Max < d) Max = d;
        }

        System.out.println(solve(Max));
    }

    static int solve(int MaxDay){
        int cnt = 0;

        for(int i=MaxDay; i>=1; i--){
            int temp = 0, idx = 0;

            for(int j=0; j<list.size(); j++){
                Node now = list.get(j);
                int day = now.d;
                int value = now.w;

                if(i <= day && temp < value){
                    temp = value;
                    idx = j;
                }
            }

            if(temp == 0) continue; // 없을 경우엔 skip

            list.remove(idx);
            cnt += temp;
        }

        return cnt;
    }

    static class Node{
        int d, w;
        public Node(int d, int w){
            this.d = d;
            this.w = w;
        }
    }
}


