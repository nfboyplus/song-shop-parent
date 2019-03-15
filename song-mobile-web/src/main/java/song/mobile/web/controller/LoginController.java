package song.mobile.web.controller;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.song.common.constants.BaseApiConstants;
import com.song.common.constants.Constants;
import com.song.common.enums.MsgCode;
import com.song.common.utils.CookieUtil;
import com.song.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import song.mobile.web.feign.UserFeign;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * created on 2019/3/10 10:00
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Slf4j
@Controller
public class LoginController extends BaseController {

    private static final String LOGIN = "login";
    private static final String INDEX = "index";
    private static final String ASSOCIATED_ACCOUNT = "associatedAccount";

    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/localLogin")
    public String localLogin(HttpServletRequest request, String source) {
        request.setAttribute("source", source);
        return LOGIN;
    }

    /**
     * 登录
     *
     * @param userEntity
     * @param source
     * @param request
     * @param httpSession
     * @param response
     * @return
     */
    @RequestMapping("/login")
    public String login(@ModelAttribute("user") UserEntity userEntity, @ModelAttribute("source") String source,
                        HttpServletRequest request, HttpSession httpSession, HttpServletResponse response) {
        if (!StringUtils.isEmpty(source) && source.equals(Constants.USER_SOURCE_QQ)) {
            String openId = (String) httpSession.getAttribute(Constants.USER_SESSION_OPENID);
            userEntity.setOpenId(openId);
        }
        Map<String, Object> login = userFeign.login(userEntity);
        Integer code = (Integer) login.get(BaseApiConstants.HTTP_CODE_NAME);
        if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
            String msg = (String) login.get("msg");
            return setError(request, msg, LOGIN);
        }
        //登录成功之后,获取token.将这个token存放在cookie
        String token = (String) login.get("data");
        CookieUtil.addCookie(response, Constants.USER_TOKEN, token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
        return INDEX;
    }

    /**
     * 跳转到QQ授权地址
     *
     * @param request
     * @return
     * @throws QQConnectException
     */
    @RequestMapping("/localQQLogin")
    public String localQQLogin(HttpServletRequest request) throws QQConnectException {
        String authorizeURL = new Oauth().getAuthorizeURL(request);
        return "redirect:" + authorizeURL;
    }

    /**
     * QQ回调地址
     *
     * @param request
     * @param httpSession
     * @param response
     * @return
     */
    @RequestMapping("/qqLoginCallback")
    public String qqLoginCallback(HttpServletRequest request, HttpSession httpSession, HttpServletResponse response) {
        try {
            //先获取accessToken
            AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
            if (accessTokenObj == null) {
                return setError(request, MsgCode.SYS_QQ_IS_FAIL.getMessage(), ERROR);
            }
            String accessToken = accessTokenObj.getAccessToken();
            if (StringUtils.isEmpty(accessToken)) {
                return setError(request, MsgCode.SYS_QQ_IS_FAIL.getMessage(), ERROR);
            }
            //获取openId
            OpenID openIdObj = new OpenID(accessToken);
            String userOpenId = openIdObj.getUserOpenID();
            //使用openId查找用户信息是否绑定
            Map<String, Object> openIdMap = userFeign.findLogin(userOpenId);
            Integer code = (Integer) openIdMap.get(BaseApiConstants.HTTP_CODE_NAME);
            //判断是否绑定会员
            if (code.equals(BaseApiConstants.HTTP_200_CODE)) {
                // 已经授权过,自动登录
                String token = (String) openIdMap.get("data");
                CookieUtil.addCookie(response, Constants.USER_TOKEN, token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
                return "redirect:/" + INDEX;
            }
            // 没有绑定openid
            httpSession.setAttribute(Constants.USER_SESSION_OPENID, userOpenId);
        } catch (Exception e) {
            log.info("QQ回调地址异常！");
        }
        // 跳转到关联页面
        return ASSOCIATED_ACCOUNT;
    }

}
