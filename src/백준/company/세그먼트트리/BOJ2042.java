package 백준.company.세그먼트트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2042 {
    // 구간 합 구하기 / 골드 1 / 세그먼트 트리
    static int N,M,K;
    static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 수의 개수
        M = Integer.parseInt(st.nextToken()); // 수의 변경 횟수
        K = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        // 구간 합 트리 인덱스 1부터 시작해서 (*2 했을 때 왼쪽 자식노드를 가리킬 수 있도록 설정)
        arr = new long[N+1];
        for(int i=1; i<=N; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        // 2^k >= N인 최소의 k를 찾아야 함.
        // 양변에 log을 취하면,
        // k >= logN / log2
        // (logN / log2)의 값을 올림한 후 1을 더하면 k가 됨.
        // 위에서 구한 k를 제곱하면 세그먼트 트리의 size를 구할 수 있음.

//		int k = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
//		int size = (int) Math.pow(2, k);
//
//		tree = new long[size];

        // 사이즈를 구하는 위의 과정이 귀찮으면, 단순히 N에 4를 곱한 사이즈를 사용해도 무방함.
        tree = new long[N*4];

        init(1, N, 1);

        // a가 1인경우 변경, 2인 경우 구간합
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1){
                long dif = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, dif);
            }else if(a == 2){
                sb.append(sumt(1, N, 1, b, (int)c) + "\n");
            }
        }

        System.out.println(sb.toString());
    }

    // start: 시작 인덱스, end: 끝 인덱스 -> init() -> 구간 합 트리 생성
    static long init(int start, int end, int node){
        if(start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;

        // 재귀적으로 두 부분으로 나눈 뒤에 그 합을 자기자신으로 함
        /**
         *  (1~mid), (mid+1 ~ end) 이렇게 두 부분으로 나눠서 그 합을 자기자신으로 함
         *  (start ~ mid, node*2 -> 왼쪽 자식노드), (mid +1 ~ end, node * 2 + 1 -> 오른쪽 자식노드)
         */
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    // 구간 합을 구하는 함수
    // start : 시작 인덱스. end : 끝 인덱스
    // left, right : 구하고자는 하는 범위
    static long sum(int start, int end, int node, int left, int right){
        // 범위 밖에 있는 경우
        if(left > end || right < start) return 0;
        // 범위 안에 있는 경우
        /**
         *  해당 범위 설정 부분이 왜 범위 안에 있는 경우인지 left <= start 부분이 이해가 잘가지 않는다
         */
        if(left <= start && end <= right) return tree[node];
        // 그렇지 않다면 두 부분으로 나누어 합을 구하기
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2 , left, right) + sum(mid + 1, end,node * 2 + 1, left, right);
    }

    // 특정 원소의 값을 수정하는 함수
    // start : 시작인덱스, end : 끝 인덱스
    // idx : 구간 합을 수정하고자 하는 인덱스, dif : 수정할 값
    static void update(int start, int end, int node, int idx, long dif){
        // 범위 밖에 있는 경우
        if (idx < start || idx > end) {
            return;
        }

        // 범위 안에 있으면 내려가며 다른 원소도 갱신
        tree[node] += dif;
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, idx, dif);
        update(mid + 1, end, node * 2 + 1, idx, dif);
    }

}
