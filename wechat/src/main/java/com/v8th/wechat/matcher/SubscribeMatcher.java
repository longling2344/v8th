package com.v8th.wechat.matcher;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;
import org.springframework.stereotype.Service;

/**
 * 关注事件
 */
@Service
public class SubscribeMatcher implements WxMessageMatcher {
    @Override
    public boolean match(WxXmlMessage message) {
        return message.getEvent().equals(WxConsts.EVT_SUBSCRIBE);
    }
}
