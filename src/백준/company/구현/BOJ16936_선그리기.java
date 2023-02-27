package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ16936_선그리기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 그린 선 개수

        List<Node> list = new ArrayList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new Node(x, y));
        }

        Collections.sort(list);

        int total = 0, firstEnd = 0;
        for(int i=0; i<N; i++) {
            Node now = list.get(i);

            if (firstEnd < now.start) {
                total += now.end - now.start;
            } else {
                if(firstEnd >= now.end) continue;
                total += now.end - firstEnd;
            }

            firstEnd = now.end;
        }
        System.out.println(total);
    }

    static class Node implements Comparable<Node>{
        int start;
        int end;

        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Node o){
            return this.start - o.start;
        }
    }
}
