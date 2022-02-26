package 코테대비.Greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 씨름선수 {
    static class Point implements Comparable<Point>{ // 키 기준으로 내림차순
        int height, weight;
        public Point(int height, int weight){
            this.height = height;
            this.weight = weight;
        }
        @Override
        public int compareTo(Point o){
            return o.height - this.height;
        }
    }
    static int n;

    public static int solution(ArrayList<Point> arr, int n){
        Collections.sort(arr);
        int max = Integer.MIN_VALUE;
        int cnt = 0;
        for (Point ob : arr){
            if(ob.weight > max){
                max = ob.weight;
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        씨름선수 T = new 씨름선수();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ArrayList<Point> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int height = sc.nextInt();
            int weight = sc.nextInt();
            arr.add(new Point(height, weight));
        }
        System.out.println(T.solution(arr, n));
    }
}
