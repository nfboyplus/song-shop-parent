package song.mobile.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * created on 2019/3/9 14:19
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Slf4j
@Controller
public class DemoController extends BaseController{

    // index页面
    public static final String INDEX = "index";

    @RequestMapping("/index")
    public String index(HttpServletRequest request, String token) {
        log.info(" 我的web工程搭建成功啦!,userName:{}", getUserEntity(token).getUserName());
        return INDEX;
    }

}
