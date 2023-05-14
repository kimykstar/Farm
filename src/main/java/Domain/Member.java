package Domain;

public class Member {

    private String id;
    private String pw;
    private String name;
    private String phone;
    private int age;

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }

    public String getId() {
        return id;
    }
}
