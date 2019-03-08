package com.song.messages.adapter;

import com.alibaba.fastjson.JSONObject;

/**
 * 功能描述:(所有消息都会交给他进行妆发)
 * created on 2019/3/8 16:28
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
public interface MessageAdapter {

    //接受消息
    public void distribute(JSONObject jsonObject);

}
