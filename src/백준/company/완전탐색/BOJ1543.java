package 백준.company.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1543 {
    // 문서 검색 / 실버4 / 완탐
    /*
        주어진 문서 문자열에
        주어진 단어가 중복되지 않게 몇 번 등장하는지 구하라
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String docu = br.readLine();
        String str = br.readLine();

        int cnt = 0;
        while (docu.length() > 0) {
            if(docu.startsWith(str)){
                cnt++;
                docu = docu.substring(str.length());
            }else{
                docu = docu.substring(1);
            }
        }


        System.out.println(cnt);
    }
}
