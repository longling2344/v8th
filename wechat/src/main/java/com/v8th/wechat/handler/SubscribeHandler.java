package com.v8th.wechat.handler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.bean.WxXmlOutNewsMessage;
import com.soecode.wxtools.exception.WxErrorException;
import com.v8th.wechat.util.WxOutMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * 关注事件，推送图文
 */
@Service
@Slf4j
public class SubscribeHandler implements WxMessageHandler {
    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService) throws WxErrorException {
        //公众号未认证没有权限，待开通后使用
        //WxUserList.WxUser user = iService.getUserInfoByOpenId(new WxUserList.WxUser.WxUserGet(wxMessage.getFromUserName(), LANG_CHINA));
        //log.info("-----news-----\n" + user.toString() + "\n\n" + WxOutMessage.NEWS(wxMessage).addArticle(newsItem(user)).build().toString());
        //return WxOutMessage.NEWS(wxMessage).addArticle(newsItem(user)).build();

        //TODO 临时方法，开通认证后删除
        WxXmlOutNewsMessage.Item item = new WxXmlOutNewsMessage.Item();
        item.setDescription(String.format("亲爱的%s你终于来了，快来看看新鲜商品吧！", "xxx"));
        item.setUrl("https://jd.com");
        item.setPicUrl("http://g.hiphotos.baidu.com/image/pic/item/f7246b600c338744dd981da85c0fd9f9d62aa080.jpg");
        return WxOutMessage.NEWS(wxMessage).addArticle(item).build();
    }

    /**
     * @param user 用户信息
     * @return 关注首推
     */
    private WxXmlOutNewsMessage.Item newsItem(WxUserList.WxUser user) {
        WxXmlOutNewsMessage.Item item = new WxXmlOutNewsMessage.Item();
        item.setDescription(String.format("亲爱的%s你终于来了，快来看看新鲜商品吧！", user.getNickname()));
        item.setUrl("https://jd.com");
        item.setPicUrl("http://g.hiphotos.baidu.com/image/pic/item/f7246b600c338744dd981da85c0fd9f9d62aa080.jpg");
        return item;
    }

}
