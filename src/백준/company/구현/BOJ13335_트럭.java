package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13335_트럭 {
    static int n, w, L;
    static int[] truck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 트럭 수
        w = Integer.parseInt(st.nextToken()); // 다리 길이
        L = Integer.parseInt(st.nextToken()); // 다리 최대하중

        Queue<Integer> q = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        truck = new int[n];
        for(int i=0; i<n; i++){ // 트럭무게
           q.offer(Integer.parseInt(st.nextToken()));
        }

        // 빈공간 = 0으로 대체
        Queue<Integer> bridge = new LinkedList<>();
        for(int i=0; i<w; i++){
            bridge.offer(0);
        }

        int time = 0;
        int totalWeight = 0;
        // 모든 트럭들이 다리를 건너는 최단시간
        while(!bridge.isEmpty()){
            time++;
            totalWeight -= bridge.poll(); // 다리의 맨 앞 트럭무게 빼주고
            if(!q.isEmpty()){ // 지나갈 트럭이 남아있다면
                if(q.peek() + totalWeight <= L){ // 남은 트럭이 다리에 올라갈 수 있는 상태면 넣어주고
                    totalWeight += q.peek();
                    bridge.offer(q.poll());
                }else{ // 아니면 0만 넣어주고 시간증가
                    bridge.offer(0);
                }
            }
        }

        System.out.println(time);
    }
}
