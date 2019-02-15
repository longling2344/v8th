package com.v8th.wechat.handler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.v8th.wechat.util.WxOutMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 返回用户 openid
 */
@Service
@Slf4j
public class OpenIdHandler implements WxMessageHandler {
    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService) {
        return WxOutMessage.TEXT(wxMessage).content(wxMessage.getFromUserName()).build();
    }
}
