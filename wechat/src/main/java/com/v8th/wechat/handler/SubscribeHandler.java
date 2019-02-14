package com.v8th.wechat.handler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.bean.WxXmlOutNewsMessage;
import com.soecode.wxtools.exception.WxErrorException;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.soecode.wxtools.api.WxConsts.LANG_CHINA;

/**
 * 关注事件，推送图文
 */
@Service
public class SubscribeHandler implements WxMessageHandler {
    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService) throws WxErrorException {
        WxUserList.WxUser user = iService.getUserInfoByOpenId(new WxUserList.WxUser.WxUserGet(wxMessage.getFromUserName(), LANG_CHINA));
        return WxXmlOutMessage.NEWS().addArticle(newsItem(user)).build();
    }

    private WxXmlOutNewsMessage.Item newsItem(WxUserList.WxUser user) {
        WxXmlOutNewsMessage.Item item = new WxXmlOutNewsMessage.Item();
        item.setTitle(String.format("亲爱的%s你终于来了，快来看看新鲜商品吧！", user.getNickname()));
        item.setUrl("https://jd.com");
        item.setPicUrl("http://g.hiphotos.baidu.com/image/pic/item/f7246b600c338744dd981da85c0fd9f9d62aa080.jpg");
        return item;
    }
}
