package song.mobile.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.song.common.constants.BaseApiConstants;
import com.song.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import song.mobile.web.feign.UserFeign;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * created on 2019/3/9 14:20
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Controller
public class BaseController {

    @Autowired
    private UserFeign userFeign;

    public UserEntity getUserEntity(String token){
        Map<String, Object> userMap = userFeign.getUser(token);
        Integer code = (Integer) userMap.get(BaseApiConstants.HTTP_CODE_NAME);
        if (!code.equals(BaseApiConstants.HTTP_200_CODE)){
            return null;
        }
        //获取data参数
        LinkedHashMap linkedHashMap = (LinkedHashMap) userMap.get(BaseApiConstants.HTTP_DATA_NAME);
        String json = new JSONObject().toJSONString(linkedHashMap);
        UserEntity userEntity = new JSONObject().parseObject(json, UserEntity.class);
        return userEntity;
    }

}
