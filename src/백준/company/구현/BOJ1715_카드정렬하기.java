package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1715_카드정렬하기 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Long> pq = new PriorityQueue(); // 가장 낮은 두 집합을 더하고, 더한 값을 포함해 모든 값 중 가장 작은 두 값을 다시 계싼해야 하기 때문에 pq사용
        for(int i=0; i<N; i++){ // 숫자카드 묶음의 크기
            pq.offer(Long.parseLong(br.readLine()));
        }

        long cnt = 0;
        while(pq.size() > 1){ // 2개가 안된다면 더이상 계산할 수 x
            long pre = pq.poll();
            long cur = pq.poll();

            cnt += pre + cur;
            pq.offer(pre + cur);
        }

        System.out.println(cnt);
    }
}
