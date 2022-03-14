package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1300 {
    // k번째 큰 수 / 골2 / 이분 탐색
    /*
        2차원 배열 A는 N * N 형태로, A배열에 들어있는 모든값들, 다 합치면 N^2개가 될텐데,
        이 값들은 1차원 배열 B에 오름차순으로 들어있다. 그래서 이 문제는 순차적인 탐색으로 풀면 시간초과남..
        이분탐색을 통해 풀어야 하는데,
        배열 A에 들어간 수들에서 K번째 큰 수를 찾는 아이디어를 생각해내야함
     */
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());
        long k = Long.parseLong(br.readLine());

        long lt = 1;
        long rt = k;
        long result = 0;

        while(lt <= rt){
            long cnt = 0;
            long mid = (lt + rt) / 2;
            /*
                mid에 대해서 1~n행까지 i번째 행에서 min(mid/i,n)을 더한 값이 k보다 작으면
                mid값을 더 큰 범위에서 찾아야 한다는 의미이고, k보다 크면 mid값 이하에서 문제에서 구하려는
                답이 있다는 의미
             */
            for(int i=1; i<=n; i++){
                cnt += Math.min(mid/i, n);
            }
            if(cnt < k){
                lt = mid + 1;
            }else{
                rt = mid - 1;
                result = mid;
            }
        }

        System.out.println(result);
    }
}
