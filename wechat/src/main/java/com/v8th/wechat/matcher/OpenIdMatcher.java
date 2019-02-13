package com.v8th.wechat.matcher;

import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.v8th.wechat.consts.VConsts;
import org.springframework.stereotype.Service;

/**
 * 用户回复关键字为 openid
 */
@Service
public class OpenIdMatcher implements WxMessageMatcher {
    @Override
    public boolean match(WxXmlMessage message) {
        return message.getContent().toLowerCase().equals(VConsts.OPENID);
    }
}
