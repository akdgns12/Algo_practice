package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 길이 M의 나무를 집에 가져가기 위해 절단기에 설정할 수 있는 높이 최댓값(ans >= 0)
public class BOJ2805_나무자르기 {
    static int N, M;
    static int tree[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 나무 수
        M = Integer.parseInt(st.nextToken()); // 가져가려는 나무 길이
        // N, M 범위가 너무커 이분탐색 ㄱ

        tree = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for(int i=0; i<N; i++){
            tree[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, tree[i]);
        }

        Arrays.sort(tree);
        // 1. 찾아야 하는건 M에 최대한 가까운 높이
        long lt = 0;
        long rt = max;

        while(lt <= rt){
            long mid = (lt + rt) / 2; // 자르는 높이
            long sum = 0;

            for(int i=0; i<N; i++){
                if(tree[i] > mid){ // mid높이로 자를 수 있는 나무라면
                    sum += (tree[i] - mid); // 자르고 가져가는 나무 더하기
                }
            }

            if(sum < M){ // sum : mid높이로 잘랐을때 가져가는 길이, 가 M보다 작다면 rt줄여야함
                rt = mid - 1;
            }else{
                lt = mid + 1;
            }
        }

        System.out.println(rt);
    }
}
