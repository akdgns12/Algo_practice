package 코테대비.DP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 가장높은탑쌓기_LIS응용 {
    static int n;
    static int[] dp;
    static ArrayList<Brick> arr;

    static class Brick implements Comparable<Brick> {
        int size, height, weight;

        public Brick(int size, int height, int weight) {
            this.size = size;
            this.height = height;
            this.weight = weight;
        }
        @Override
        public int compareTo(Brick o){ //넓이기준 내림차순
            return o.size - this.size;
        }
    }

    public static int solution(ArrayList<Brick> arr){
        int answer = 0;
        Collections.sort(arr);
        dp[0] = arr.get(0).height;
        answer = dp[0];
        for (int i = 1; i < arr.size(); i++) {
            int maxHegiht = 0;
            for (int j = i - 1; j >= 0; j--) {
                if(arr.get(j).weight > arr.get(i).weight && dp[j] > maxHegiht){
                    maxHegiht = dp[j];
                }
            }
            dp[i] = maxHegiht + arr.get(i).height;
            answer = Math.max(answer, dp[i]);
        }


        return answer;
    }
    public static void main(String[] args) {
        가장높은탑쌓기_LIS응용 T = new 가장높은탑쌓기_LIS응용();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new ArrayList<Brick>();
        dp = new int[n];
        for (int i = 0; i < n; i++) {
            int size = sc.nextInt();
            int height = sc.nextInt();
            int weight = sc.nextInt();
            arr.add(new Brick(size, height, weight));
        }
        System.out.println(T.solution(arr));
    }
}

