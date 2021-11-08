package day06.mission3.accountbook;

public class User {

    private String name;
    private String password;

    User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User temp = (User) obj;
            return name.equals(temp.name);
        }

        return false;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
