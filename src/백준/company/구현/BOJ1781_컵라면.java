package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1781_컵라면 {
    static int N;
    static List<Node> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.add(new Node(deadLine, cost));
        }

        System.out.println(solve());
    }

    static int solve(){
        int cnt = 0;
        

        return cnt;
    }

    static class Node{
        int deadLine, cost;
        public Node(int deadLine, int cost){
            this.deadLine = deadLine;
            this.cost = cost;
        }
    }
}
