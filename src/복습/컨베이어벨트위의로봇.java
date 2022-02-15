package 복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 컨베이어벨트위의로봇 {
    static int N, K;
    static int[] belt;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[N*2];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N*2; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;
        while(true){
            rotateBelt();
            count++;
            moveRobot();

            if(!check()){
                System.out.println(count);
                break;
            }
        }
    }

    // 벨트와 로봇이 함께 회전하는 함수
    static void rotateBelt(){
        // 벨트 한칸 이동
        int temp = belt[belt.length-1]; // 마지막값 임시저장

        for(int i=belt.length-1; i>0; i--){
            belt[i] = belt[i-1];
        }

        belt[0] = temp; // 올라오는 칸 덮어쓰기

        // 로봇이동
        for(int i=robot.length-1; i>0; i--){
            robot[i] = robot[i-1];
        }

        robot[0] = false; // 로봇이 올라오는 곳은 반드시 비워야 한다.
    }

    // 로봇 스스로 벨트 회전방향으로 이동하는 함수
    static void moveRobot(){
        // 내리는 칸에서 로봇 제거해주기
        if(robot[N-1])
            robot[N-1] = false;

        // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동
        for(int i=N-2; i>=0; i--){
            // 2. 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없고, 그 칸의 내구도가 1 이상 남아있어야 함
            if(robot[i] && belt[i+1] > 0 && !robot[i+1]){
                robot[i+1] = true;
                robot[i] = false;
                belt[i+1]--; // 내구도 감소
            }
        }

        // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다
        if(belt[0] > 0){
            robot[0] = true;
            belt[0]--;
        }
    }

    static boolean check(){
        int check = 0;

        for(int i=0; i<belt.length; i++){
            if(belt[i] == 0)
                check++;
        }

        if(check >= K)
            return false;
        else
            return true;
    }
}
