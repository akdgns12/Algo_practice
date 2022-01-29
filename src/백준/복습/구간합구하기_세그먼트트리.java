package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기_세그먼트트리 {
    static int N, M, K;
    static long[] tree;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N+1];

        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }


        tree = new long[N*4];

        init(1, N, 1);


        StringBuilder sb = new StringBuilder();
        for(int i=0; i<M+K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1){ // 구간 수 변경 2
                long dif = c - arr[b];
                arr[b] = c;
                update(1,N, 1, b, dif);
            }else if(a == 2){ // 구간 합 구하기
                sb.append(sum(1,N,1,b,(int)c )+ "\n");
            }
        }

        System.out.println(sb.toString());
    }

    // 구간 합 트리 생성
    static long init(int start, int end, int node){
        if(start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;

        return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
    }

    // 구간 합 구하는 함수
    static long sum(int start, int end, int node, int left, int right){
        if(left > end || right < start) return 0;

        if(left <= start && end <= right){
            return tree[node];
        }

        int mid = (start + end) / 2;
        return tree[node] = sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
    }

    static void update(int start, int end, int node, int idx, long dif){
        // [start, end]에 idx가 포함되지 않는 경우
        if(idx < start || idx > end) return;

        // [start,end]에 idx가 포함되는 경우
        // 범위 안에 있으면 내려가며 다른 원소도 갱신
        tree[node] = tree[node] + dif;
        if(start == end){
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node*2, idx, dif);
        update(mid+1, end, node*2+1, idx, dif);
    }
}
