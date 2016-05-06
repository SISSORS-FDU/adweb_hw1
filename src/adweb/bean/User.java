package adweb.bean;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * Created by 张亚中 on 2016-05-05.
 */
@Component
public class User {
    private int id;
    private String account;
    private String name;
    private String password;
    private int age;

    public User() {
    }

    public User(int id, String account, String name, String password, int age) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public JSONObject toJSONObject() {
        JSONObject user = new JSONObject();
        try {
            user.put("id", id);
            user.put("account", account);
            user.put("name", name);
            user.put("age", age);
            user.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }
}
