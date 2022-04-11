package Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Number1 {
    public static void main(String[] args) {
        Number1 T = new Number1();
        int[][] dist = {{0, 2, 3, 1},
                {2, 0, 1, 1},
                {3, 1, 0, 2},
                {1, 1, 2, 0}};
        System.out.println(T.solution(dist));
    }

    static int[][] dp;

    public int[][] solution(int[][] dist) {
        int[][] answer = {};



        return answer;
    }
}
