package SSAFY.polymorphism.step6;

public class VipUser extends User{
    private String grade;
    private int point;

    public VipUser() {
        super(); // 부모 클래스의 생성자 호출
    }

    public VipUser(String id, String password, String name, String email, int age, String grade, int point) {
        super(id, password, name, email, age); // 부모 클래스의 생성자 호출
        this.grade = grade;
        this.point = point;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        // 여기서 this는 VipUser의 생성자 호출인데,
        // VipUser 클래스에서 부모 클래스의 생성자를 호출한다면?
        // this.getId()가 부모 클래스에서 id에 접근하는 동작을 할 것 같은데?
        return "VipUser [id=" + this.getId() + ", password=" + this.getPassword() + ", name=" + this.getName()
                + ", email=" + this.getEmail() + ", age=" + this.getAge() + ", grade=" + grade + ", point=" + point
                + "]";
    }
}
