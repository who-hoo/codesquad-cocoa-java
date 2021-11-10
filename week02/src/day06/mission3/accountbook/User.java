package day06.mission3.accountbook;

public class User {

    private final String name;
    private final String password;

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

    public boolean correctName(String name) {
        return this.name.equals(name);
    }

    public boolean correctPassword(String password) {
        return this.password.equals(password);
    }
}
