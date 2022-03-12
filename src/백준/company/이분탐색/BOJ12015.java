package 백준.company.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ12015 {
    // 가장 긴 증가하는 부분수열2 / 골드 2 / DP로 풀게되면 O(n^2)으로 시간초과, 이분탐색의 lower_bound를 이용해 O(nlogn)으로 풀어내야 한다
    /*
        1. 리스트의 마지막 보다 크면 삽입
        2. 리스트의 마지막 보다 작으면 리스트 이진탐색으로 적절한 위치에 삽입.

        lowerBound, UppperBound
        Lower Bound와 Upper Bound는 일종의 이분 탐색에서 파생된 것으로,
        이분 탐색이 '원하는 값 k를 찾는 과정' 이라면
        Lower Bound는 '원하는 값 k 이상이 처음 나오는 위치를 찾는 과정' 이며,
        Upper Bound는 '원하는 값 k를 초과한 값이 처음 나오는 위치를 찾는 과정'이다.

     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> result = new ArrayList<>();
        result.add(arr[0]); //ArrayList를 empty로 만들지 않는다.


        //O(n) * O(logn) = O(nlogn)
        for(int i=1; i<arr.length; i++){
            int lower = lowerBound(result, 0, result.size(), arr[i]);

            //삽입할 위치가 맨 뒤일 때
            if(result.size() <= lower) result.add(arr[i]);
            //삽입할 위치에 이미 숫자가 존재한다면 바꾼다.
            else result.set(lower, arr[i]); // set - 특정 인덱스값을 대체
        }

        System.out.println(result.size());
    }

    static int lowerBound(ArrayList<Integer> result, int lt, int rt, int key){
        int mid = 0;
        while(lt < rt){
            mid = (lt + rt) / 2;
            if(result.get(mid) < key)
                lt = mid + 1;
            else
                rt = mid;
        }
        return rt;
    }
}
