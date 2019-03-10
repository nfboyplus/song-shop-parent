package song.mobile.web.controller;

import com.song.common.constants.BaseApiConstants;
import com.song.common.constants.Constants;
import com.song.common.utils.CookieUtil;
import com.song.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import song.mobile.web.feign.UserFeign;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * created on 2019/3/10 10:00
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Controller
public class LoginController extends BaseController{

    private static final String LGOIN = "login";
    private static final String INDEX = "index";

    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/localLogin")
    public String localLogin(){
        return LGOIN;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, UserEntity userEntity){
        Map<String, Object> login = userFeign.login(userEntity);
        Integer code = (Integer) login.get(BaseApiConstants.HTTP_CODE_NAME);
        if (!code.equals(BaseApiConstants.HTTP_200_CODE)){
            String msg = (String) login.get("msg");
            return setError(request, msg, LGOIN);
        }
        //登录成功之后,获取token.将这个token存放在cookie
        String token = (String) login.get("data");
        CookieUtil.addCookie(response, Constants.USER_TOKEN, token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
        return INDEX;
    }
}
