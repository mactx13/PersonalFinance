package OOP_HW;

public class User {
    private String login;
    private String password;
    private Wallet wallet;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.wallet = new Wallet();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
