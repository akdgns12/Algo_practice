package 백준.company.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2075 {
    // N번째 큰 수 / 골드 5 / 정렬
    /*
        우선순위 큐는 기본적으로 오름차순으로 데이터를 정렬해주기 때문에 이를 이용하여
        슬라이딩 윈도우 알고리즘에 적용하여 풀면 된다
        데이터를 차례대로 입력받아 큐의 사이즈가 N에 도달했을 때, 새로 들어온 값과 큐에
        들어있는 가장 작은 값(q.peek())을 비교하여 새로 들어온 값이 더 클 경우 데이터를
        교체해주는 방식. 이는 구간 N만 탐색하여 계산이 가능하므로 시간복잡도 O(N)을 가진다.

        그리고 큐의 최대 사이즈는 N으로 계속해서 데이터를 삭제, 삽입 해주므로 모든 데이터 N^2
        를 저장하여 정렬하는 방식보다 공간복잡도의 효율을 높여줄 수 있다.
     */
    /**
     * 로직
     * 1. 우선순위 큐에 숫자를 담는다
     * 2. 큐의 사이즈가 N이 되면 맨 앞의 숫자(N개의 수 중 가장 작은 숫자)와 새로운 값을 비교
     *  2-1. 새로운 값이 더 클 경우 데이터 바꿔준다
     *  2-2. 새로운 값이 더 작을 경우 그대로 유지
     */
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (pq.size() == N) {
                    if (pq.peek() < num) {
                        pq.poll();
                        pq.add(num);
                    }
                } else {
                    pq.add(num);
                }
            }
        }

        System.out.println(pq.poll());
    }
}
