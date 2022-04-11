package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4673 {
    // 셀프넘버 / 실버 5 / 구현
    // 조건에 맞는 생성자가 없는 즉, 셀프넘버를 출력 (10000보다 작거나 같은)
    /*
        생각생각생각!
        n으로 d(n)이 만들어진다면 만들어진 d(n)은 셀프넘버가 아니다!
        그렇다면 1~만까지 n으로 d(n)을 만들고 만든 d(n)은 체크배열에서 true로 체크해줌
        반복을 마친 후 체크되지 않은 것들이 셀프넘버다!
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] isNotSelfNumber = new boolean[10001];

        for (int i = 1; i <= 10000; i++) {
            int dn = getDn(i);
            if(dn <= 10000){
                isNotSelfNumber[dn] = true;
            }
        }

        for (int i = 1; i < isNotSelfNumber.length; i++) {
            if(!isNotSelfNumber[i])
                System.out.println(i);
        }
    }

    static int getDn(int x){
        int dn = x;
        while(x > 0){
            dn += x % 10;
            x /= 10;
        }
        return dn;
    }
}
