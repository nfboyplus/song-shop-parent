package song.mobile.web.feign;

import com.song.api.service.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * created on 2019/3/9 14:37
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@FeignClient("shop-member")
public interface UserFeign extends UserService {

}
