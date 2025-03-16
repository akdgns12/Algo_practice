package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2457_공주님정원 {
    static int N;
    static List<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 꽃 개수

        list = new ArrayList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());

            list.add(new Node(start, end));
        }

        Collections.sort(list);

        int max = 0, ans = 0;
        int start = 301;
        int idx = 0;
        while(start < 1201){
            boolean isPossible = false;

            for(int i=idx; i<N; i++) {

                if(list.get(i).start > start) break;

                //a값이 max값보다 작은 것들 중 b의 값이 가장 큰것을 선택
                if(list.get(i).start <=start && max<list.get(i).end) {
                    max = list.get(i).end;
                    idx = i+1;
                    isPossible = true;
                }
            }

            if(isPossible) {
                start = max;
                ans++;
            }else break;
        }

        if(max<1201) System.out.println(0);
        else System.out.println(ans);
    }

    static class Node implements Comparable<Node>{
        int start, end;
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
        // 시작 기준 오름, 종료기준 내림
        @Override
        public int compareTo(Node o){
            if(this.start == o.start) return o.end - this.end;
            else return this.start - o.start;
        }
    }
}
