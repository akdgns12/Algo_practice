package 백준.company.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20436 {
    // 양 손 동시입력은 안됨.
    /*
        한 번에 한손가락만 입력 가능
     */
    static char[][] keyboard = {
            {'q','w','e','r','t','y','u','i','o','p'},
            {'a','s','d','f','g','h','j','k','l'},
            {'z','x','c','v','b','n','m'}
    };
    static char curR, curL;
    static int time = 0;
    /*
        입력으로 주어진 문자열을 출력하는 데에 걸리는 시간의 min time return
     */
    // 로직 : string 배열로 키보드를 입력시켜놓고
    // 주어지는 초기위치를 받은 다음
    // string배열을 for문으로 돌면서 일치하는 인덱스 위치를 0으로 초기화 시켜놓고
    // 문제로 주어진 문자열을 돌면서 거리가 얼마만큼 차이나는지 count ++?
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        curL = st.nextToken().charAt(0);
        curR = st.nextToken().charAt(0);
        String str = br.readLine();

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);

            int[] next = findIndex(ch); // 입력하려는 숫자의 키보드상 위치 구하기(행,열)

            // 오른손으로 입력해야하는 문자인 경우
            if(ch == 'y' || ch == 'u' || ch == 'i' || ch == 'o'
            ||ch == 'p' ||ch == 'h' ||ch == 'j' || ch == 'k' ||ch == 'l'
            || ch == 'b' || ch == 'n' ||ch == 'm'){
                int now[] = findIndex(curR); // 현재 오른손의 위치 구하기
                // 현재 오른손의 위치에서 입력하려는 숫자로 이동하는데 걸리는 시간 구하기
                time += calTime(now[0], now[1], next[0], next[1]);

                curR = ch; // 현재 위치 갱신
            }else{// 왼손으로 입력해야하는 문자인 경우
                int now[] = findIndex(curL);
                time += calTime(now[0], now[1], next[0], next[1]);

                curL = ch;
            }

            time += 1; // 입력하는데 1초
        }

        System.out.println(time);
    }

    static int calTime(int x, int y, int x2, int y2){
        return Math.abs(x - x2) + Math.abs(y - y2);
    }

    static int[] findIndex(char c){
        int[] index = new int[2];

        for(int i=0; i<keyboard.length; i++){
            for(int j=0; j<keyboard[i].length; j++){
                if(keyboard[i][j] == c){
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index; // 인덱스 0에는 행, 1에는 열 저장해서 return
    }
}
