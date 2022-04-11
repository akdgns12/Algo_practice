package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2941 {
    // 크로아티아 알파벳 / 실버5 / 구현
    /*
        생각생각
        문제에서 주어진 크로아티아 알파벳 6개
        입력된 문자열에 주어진 크로아티아 알파벳 중 해당하는 문자가 있는지 확인하고
        있다면 "*"로 대체
        마지막엔 최종적으로 변경된 문자의 길이가 사용된 크로아티아 알파벳의 개수
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        String str = br.readLine();

        int result = 0;

        for (int i = 0; i < croatia.length; i++) {
            if(str.contains(croatia[i])){
                str = str.replaceAll(croatia[i], "*");
            }
        }

        result = str.length();
        System.out.println(result + "");
    }
}
