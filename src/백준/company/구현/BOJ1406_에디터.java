package 백준.company.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1406_에디터 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String origin = br.readLine();
        int len = origin.length();

        int M = Integer.parseInt(br.readLine()); // 명령어 개수

        int cursor = len; // 초기 위치는 맨 뒤
        Stack<Character> left = new Stack<>();
        for(int i=0; i<len; i++) left.push(origin.charAt(i));
        Stack<Character> right = new Stack<>();

        for(int i=0; i<M; i++){
            String command = br.readLine();
            char ch = command.charAt(0);

            switch (ch){
                case 'L':
                    if(!left.isEmpty()) right.push(left.pop());
                    break;
                case 'D':
                    if(!right.isEmpty()) left.push(right.pop());
                    break;
                case 'B':
                    if(!left.isEmpty()) left.pop();
                    break;
                case 'P':
                    char word = command.charAt(2);
                    left.push(word);
                    break;
            }
        }

        while(!left.isEmpty()) right.push(left.pop());
        while(!right.isEmpty()) sb.append(right.pop());
        System.out.println(sb);
    }
}
