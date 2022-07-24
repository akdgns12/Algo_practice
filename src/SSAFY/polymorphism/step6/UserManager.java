package SSAFY.polymorphism.step6;

import java.util.Arrays;

public class UserManager {
    int MAX_SIZE = 100;
    int size = 0;
    User[] userList = new User[MAX_SIZE];

    public void add(User user) {
        if(size < MAX_SIZE) userList[size++] = user;
        else System.out.println("유저의 수가 100을 넘었습니다");
    }

    // 전체 유저리스트를 반환하는 메서드
    public User[] getList(){
        return Arrays.copyOfRange(userList, 0, size);
    }

    // 일반 사용자들만 반환하는 메서드
    public User[] getUsers(){
        int cnt = 0;
        // 전체 사용자들 중 VipUser에 해당하지 않는 유저만 카운팅
        for (int i = 0; i < this.size; i++) {
            if (!(userList[i] instanceof VipUser)) {
                cnt++;
            }
        }

        // cnt크기의 결과 배열 생성
        User[] result = new User[cnt];
        int idx = 0;

        // 전체 유저중 VipUser에 해당하지 않는 유저만 배열에 담아 리턴
        for (int i = 0; i < this.size; i++) {
            if (!(userList[i] instanceof VipUser)) {
                result[idx++] = userList[i];
            }
        }

        return result;
    }

    // VipUser들만 반환하는 메서드
    public VipUser[] getVipUsers(){
        int cnt = 0;
        for (int i = 0; i < this.size; i++) {
            if (userList[i] instanceof VipUser) {
                cnt++;
            }
        }

        VipUser[] result = new VipUser[cnt];
        int idx = 0;
        for (int i = 0; i < size; i++) {
            if (userList[i] instanceof VipUser) {
                result[idx++] = (VipUser)userList[i];
            }
        }

        return result;
    }

    // 해당 이름 일부 또는 전체를 포함하는 사용자 리스트 반환 메서드
    public User[] searchByName(String name) {
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if (userList[i].getName().toLowerCase().contains(name.toLowerCase())) {
                cnt++;
            }
        }

        User[] result = new User[cnt];
        int idx = 0;
        for (int i = 0; i < size; i++) {
            if (userList[i].getName().toLowerCase().contains(name.toLowerCase())) {
                result[idx++] = userList[i];
            }
        }

        return result;
    }

    // 사용자의 평균 나이를 반환하는 메서드
    public double getAgeAvg(){
        int total = 0;

        for (int i = 0; i < size; i++) {
            total += userList[i].getAge();
        }

        return (double)total / size;
    }
}

