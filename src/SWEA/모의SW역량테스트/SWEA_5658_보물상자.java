package SWEA.모의SW역량테스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_5658_보물상자 {
    static int N, K;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 숫자의 개수
            K = Integer.parseInt(st.nextToken()); // 크기 순서(최종 찾는 수)

            // N개의 수가 한줄로 주어짐
            String input = br.readLine();

            List<String> result = new ArrayList<>(); // 정답 문자열 넣어놓을 list
            List<Character> list = new ArrayList<>(); // input 원본 list
            List<Character> change; // rotate 할때마다 변화된 문자들을 담을 list
            List<String> calc;

            for(int i=0; i<input.length(); i++) list.add(input.charAt(i));

            for(int rotate=0; rotate<3; rotate++){ // 한칸씩 뒤로 밀자
                change = new ArrayList<>();

                for(int i=list.size() - 1 - rotate; i<list.size(); i++){
                    char tmp = list.get(i);
                    change.add(tmp);
                }

                for(int i=0; i<list.size() - 1 - rotate; i++){
                    char tmp = list.get(i);
                    change.add(tmp);
                }

//                for(int i=rotate; i<input.length(); i++){
//                    char tmp = list.get(i);
//                    change.add(tmp);
//                }
//
//
//                for(int i=rotate-1; i>=0; i--){
//                    char tmp = list.get(i);
//                    change.add(tmp);
//                }

                calc = new ArrayList<>();
                String tmp = "";
                for(int i=0; i<change.size(); i++){
                    tmp += change.get(i);
                }

                calc.add(tmp);

                for(int i=0; i<=calc.get(0).length() - N/4; i += N/4){
                    String temp = "";

                    for(int j=i; j<i+N/4; j++){
                        temp += calc.get(0).charAt(j);
                    }

                    // 중복제거
                    if(result.contains(temp)) continue;
                    result.add(temp);
                }
            }

            // result에 있는 문자들 내림차순 정렬
            Collections.sort(result, Collections.reverseOrder());

            String temp = result.get(K-1);
            ans = Integer.parseInt(temp, 16);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

}
