package adweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 张亚中 on 2016-05-06.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/")
    public String index() {
        return "login";
    }
}
