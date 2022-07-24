package SSAFY.polymorphism.step6;

public class UserTest {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setId("user1");
        user1.setPassword("user1");
        user1.setName("김싸피");
        user1.setEmail("ssafy1@ssafy.com");
        user1.setAge(27);

        User user2 = new User("user2", "user2", "박싸피", "ssafy2@ssafy.com", 28);
        User user3 = new User("user3", "user3", "김재호", "ssafy2@ssafy.com", 32);
        VipUser vuser = new VipUser("vip1", "vip1", "김싸피" , "ssafy3@ssafy.com", 29, "Gold", 300);

        UserManager um = new UserManager();

        um.add(user1);
        um.add(user2);
        um.add(user3);
        um.add(vuser);
        // 전체 사용자 리스트
        for (User data : um.getList()) {
            System.out.println(data);
        }
        System.out.println();

        // "김" 이라는 단어를 이름에 포함하는 사용자 검색 결과 toString 으로 출력
        for (User data : um.searchByName("김")) {
            System.out.println(data);
        }
        System.out.println();

        // 일반 사용자 리스트만 가져와서 toString 으로 출력
        for (User data : um.getUsers()) {
            System.out.println(data);
        }
        System.out.println();

        // 사용자들의 평균 나이 출력
        System.out.println(um.getAgeAvg());
    }
}
