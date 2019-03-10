package song.mobile.web.controller;

import com.song.common.constants.BaseApiConstants;
import com.song.common.enums.MsgCode;
import com.song.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import song.mobile.web.feign.UserFeign;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * created on 2019/3/9 15:18
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Controller
public class RegisterController extends BaseController {

    private static final String LGOIN = "login";
    private static final String LOCALREGISTER = "localRegister";

    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/localRegister")
    public String localRegister() {
        return LOCALREGISTER;
    }

    /**
     * 注册
     * @param request
     * @param userEntity
     * @return
     */
    @RequestMapping("/register")
    public String register(HttpServletRequest request, UserEntity userEntity) {
        try {
            Map<String, Object> registerMap = userFeign.register(userEntity);
            Integer code = (Integer) registerMap.get(BaseApiConstants.HTTP_CODE_NAME);
            if (!code.equals(BaseApiConstants.HTTP_200_CODE)){
                String msg = (String) registerMap.get("msg");
                return setError(request, msg, LOCALREGISTER);
            }
            //注册成功
            return LGOIN;
        } catch (Exception e) {
            return setError(request, MsgCode.SYS_REGISTER_FAIL.getMessage(), LOCALREGISTER);
        }
    }

}
