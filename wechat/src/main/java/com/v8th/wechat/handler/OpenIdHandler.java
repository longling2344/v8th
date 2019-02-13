package com.v8th.wechat.handler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 返回用户 openid
 */
@Service
public class OpenIdHandler implements WxMessageHandler {
    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService) {
        return WxXmlOutMessage.TEXT().content(wxMessage.getFromUserName()).build();
    }
}
