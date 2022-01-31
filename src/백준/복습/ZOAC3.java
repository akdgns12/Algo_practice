package 백준.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ZOAC3 {
    static char[][] keyboard = {
            {'q','w','e','r','t','y','u','i','o','p'},
            {'a','s','d','f','g','h','j','k','l'},
            {'z','x','c','v','b','n','m'}
    };
    static char curR, curL;
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());
        curR = st.nextToken().charAt(0);
        curL = st.nextToken().charAt(0);
        String str = br.readLine();

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);

            // 왼손으로 입력해야할 문자라면
            if(ch == 'q' || ch == 'w' || ch == 'e' || ch == 'r' || ch == 't'
            || ch == 'a' || ch == 's' || ch == 'd' || ch == 'f' || ch == 'g'
            || ch == 'z' || ch == 'x' || ch == 'c' || ch == 'v'){
                // 다음 입력할 문자의 위치를 2차원 배열로 행, 열 형태로 받아옴
                int[] next = findIndex(ch);
                // 현재 왼손의 위치
                int[] now = findIndex(curR);

                time += calTime(now[0],now[1],next[0],next[1]);

                curL = ch;
            }else { // 오른손으로 입력해야할 문자라면

                int[] next = findIndex(ch);
                // 현재 오른의 위치
                int[] now = findIndex(curR);

                time += calTime(now[0],now[1],next[0],next[1]);
            }

            time+=1; // 입력하는데 걸리는 시간 1초
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
                if(c == keyboard[i][j]){
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index;
    }
}
