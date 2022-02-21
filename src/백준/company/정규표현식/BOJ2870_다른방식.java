package 백준.company.정규표현식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2870_다른방식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<BigInteger> result = new ArrayList<>();
        String[] line;

        for (int i = 0; i < n; i++) {
            // \D - 숫자가 아닌 항목을 일치시킨다
            line = br.readLine().split("\\D");
            for (int j = 0; j < line.length; j++) {
                if(!line[j].equals("")) result.add(new BigInteger(line[j]));
            }
        }

        Collections.sort(result);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
        br.close();
    }
}
