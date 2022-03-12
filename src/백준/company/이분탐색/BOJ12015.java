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

    /*
        배열을 탐색하면서 만들 수 있는 가장 긴 수열을 만들면서 진행한다.

arr 배열이 [10, 20, 10, 30, 20, 50]이 존재한다고 할 때,

rs는
10 [10] ➡ 20 [10, 20] ➡ 10 [10, 20] ➡ 30 [10, 20, 30] ➡ 20 [10, 20, 30] ➡ 50 [10, 20, 30, 50]
와 같이 배열이 변한다.

lower bound의 성질에 의해 내 현재 Key(arr[i]번째 수)보다 작은 위치를 return 한다.



위와 같이 배열을 만들면서 갈 수가 있는 것은, 배열을 진행하면서 만들 수 있는 가장 긴 증가하는 부분수열을 만들기 때문이다.

arr[i]의 숫자보다 작아야하니까, arr[i]보다 큰 숫자의 위치를 arr[i]가 빼았는다.



📍 그러나 rs가 올바른 LIS 배열을 가지지는 않는다.

[10, 20, 25, 18, 50]인 배열일 때,

rs는
10 [10] ➡ 20 [10, 20] ➡ 25 [10, 20, 25] ➡ 18 [10, 18, 25] ➡ 50 [10, 18, 25, 50]
와 같이 배열이 변한다.

밑줄 친 배열의 부분은 18보다 25가 더 먼저 나타나있으므로, 올바른 증가하는 부분 수열이라고 할 수 없다.

그러나 개수만 보기 때문에 상관 없다. 20의 역할을 18이 잠시 맡고 있는 것이다.

이후에 18과 25 사이의 숫자가 나타나더라도, 25보다 작은 숫자가 제일 마지막을 차지하면 되고, 18은 자연스럽게 증가하는 부분 수열을 이루는 원소로 작용할 수 있다.



출처: https://honeywater97.tistory.com/164 [HoneyWater]
     */
}
