package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ25757_임스와함께하는미니게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 함께 플레이할 사람 수
        char game = st.nextToken().charAt(0); // 플레이할 게임 종류

        HashSet<String> set = new HashSet<>();
        int cnt = 0;
        for(int i=0; i<N; i++){
            String name = br.readLine();
            if(set.contains(name)) {
                continue;
            }else{
                set.add(name);
                cnt += 1;
            }
        }

        int turn = -1;
        switch(game){
            case 'Y':
                turn = 1;
                break;
            case 'F':
                turn = 2;
                break;
            case 'O':
                turn = 3;
                break;
        }
        System.out.println(cnt / turn);
    }
}
