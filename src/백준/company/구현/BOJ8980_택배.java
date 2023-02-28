package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ8980_택배 {
    static int N, C, M;
    static List<Node> info = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); // 마을 수
        C = Integer.parseInt(st.nextToken()); // 트럭 용량

        M = Integer.parseInt(br.readLine()); // 박스 정보 수

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int send = Integer.parseInt(st.nextToken()); // 보내는 마을 번호
            int receive = Integer.parseInt(st.nextToken()); // 받는 마을 번호
            int sendBox = Integer.parseInt(st.nextToken()); // 보내는 박스 수

            info.add(new Node(send, receive, sendBox));
        }


    }

    static class Node implements Comparable<Node>{
        int send;
        int receive;
        int boxNum;

        public Node(int send, int receive, int boxNum){
            this.send = send;
            this.receive = receive;
            this.boxNum = boxNum;
        }

        /**
         * 받는 마을 기준 오름차순
         * 같다면 보내는 마을 기준 오름차순
         */
        @Override
        public int compareTo(Node o){
            if(this.receive == o.receive){
                return this.send - o.send;
            }else return this.receive - o.receive;
        }
    }
}
