package SSAFY.polymorphism.step6;

public class User {
    private String id;
    private String password;
    private String name;
    private String email;
    private int age;

    public User(){}

    public User(String id, String password, String name, String email, int age) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User [id =");
        sb.append(id);
        sb.append(", password =");
        sb.append(password);
        sb.append(", name=");
        sb.append(name);
        sb.append(", email=");
        sb.append(email);
        sb.append(", age=");
        sb.append(age);
        sb.append("]");

        return sb.toString();
    }
}
