package adweb.controller;

import adweb.bean.User;
import adweb.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by 张亚中 on 2016-05-06.
 */
@Controller
@RequestMapping(value = "/user.do")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(params = "method=login")
    public String login(String account, String password, HttpSession httpSession) throws JSONException {
        User user = userService.findUserByAccount(account);
        if (user != null && user.getPassword().equals(password)) {
            httpSession.setAttribute("user", user);
            return "info";
        }
        return "login";
    }

    @RequestMapping(params = "method=register")
    public String register(String account, String password, String name, int age, HttpSession httpSession) throws JSONException {
        User user = new User(0, account, name, password, age);
        if (userService.addUser(user)) {
            user = userService.findUserByAccount(account);
            httpSession.setAttribute("user", user);
            return "info";
        }
        return "register";
    }

    @RequestMapping(params = "method=edit")
    public String edit(String account, String password, String name, int age, int id, HttpSession httpSession) throws JSONException {
        User user = new User(id, account, name, password, age);
        userService.updateUser(user);
        httpSession.setAttribute("user", user);
        return "info";
    }

    @ResponseBody
    @RequestMapping(params = "method=getinfo", produces = "text/plain; charset=utf-8")
    public String edit(HttpSession httpSession) throws JSONException {
        JSONObject response = new JSONObject();
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            response.put("status", "0");
            response.put("user", user.toJSONObject());
        } else {
            response.put("status", "1");
        }
        return response.toString();
    }
}
