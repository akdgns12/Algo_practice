package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class test {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        map = new char[10][10];

        for(int i=0; i<10; i++){
            String str = br.readLine();
            for(int j=0; j<10; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'H')
                    System.out.println(map[i][j]);
            }
        }

        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
