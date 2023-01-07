package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4659_비밀번호발음하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // a,e,i,o,u 중 하나 반드시 포함
        // 모음이 3개 혹은 자음 3개 연속 x
        // 같은 글자가 연속적으로 두 번 오면 안되나, ee와 oo는 허용

        while(true){
            String str = br.readLine();
            if(str.equals("end")) break;

            sb.append("<" + str + ">").append(" is ");
            if(check(str)) sb.append("acceptable.").append("\n");
            else sb.append("not acceptable.").append("\n");
        }

        System.out.println(sb);
    }

    static boolean check(String str){
        boolean isIn = false;
        int moCnt = 0, jaCnt = 0;
        for(int i=0; i<str.length(); i++){
            // 1.
            if(isMo(str.charAt(i))) {
                isIn = true;
                moCnt++;
                jaCnt = 0;
            }else{
                jaCnt++;
                moCnt = 0;
            }

            if(jaCnt >= 3 || moCnt >= 3) return false;
        }

        for(int i=1; i<str.length(); i++){
            if(str.charAt(i) == str.charAt(i-1)){
                if(str.charAt(i) != 'e' && str.charAt(i) != 'o'){
                    return false;
                }
            }
        }

        if(!isIn) return false;
        return true;
    }

    static boolean isMo(Character c){
        if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') return true;
        else return false;
    }
}
