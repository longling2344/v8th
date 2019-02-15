package com.v8th.wechat.controller;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.util.xml.XStreamTransformer;
import com.v8th.wechat.handler.OpenIdHandler;
import com.v8th.wechat.handler.SubscribeHandler;
import com.v8th.wechat.matcher.OpenIdMatcher;
import com.v8th.wechat.matcher.SubscribeMatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import static com.v8th.wechat.consts.VConsts.*;

/**
 * 微信回调接口
 */
@RestController
@RequestMapping("/wx")
@Slf4j
public class CallbackController {

    private IService iService;
    private OpenIdMatcher openIdMatcher;
    private OpenIdHandler openIdHandler;
    private SubscribeMatcher subscribeMatcher;
    private SubscribeHandler subscribeHandler;

    @Autowired
    public CallbackController(IService iService, OpenIdMatcher openIdMatcher, OpenIdHandler openIdHandler, SubscribeMatcher subscribeMatcher, SubscribeHandler subscribeHandler) {
        this.iService = iService;
        this.openIdMatcher = openIdMatcher;
        this.openIdHandler = openIdHandler;
        this.subscribeMatcher = subscribeMatcher;
        this.subscribeHandler = subscribeHandler;
    }

    /**
     * 校验签名
     */
    @GetMapping
    public String check(String signature, String timestamp, String nonce, String echostr) {
        log.info("-------into get method-----");
        boolean chkResult = iService.checkSignature(signature, timestamp, nonce, echostr);
        return chkResult ? echostr : CHKFAIL;
    }

    /**
     * @param xml xml
     * @return 参考 https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140543
     */
    @PostMapping
    @ResponseBody
    public String execute(@RequestBody String xml){
        log.info("-------into post method-----\n{}",xml);
        WxMessageRouter router = new WxMessageRouter(iService);
        WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, xml);
        router.rule().msgType(WxConsts.XML_MSG_TEXT).matcher(openIdMatcher).handler(openIdHandler).end()
                .rule().msgType(WxConsts.XML_MSG_EVENT).matcher(subscribeMatcher).handler(subscribeHandler).end();

        // 把消息传递给路由器进行处理
        WxXmlOutMessage xmlOutMsg = router.route(wx);
        return xmlOutMsg != null ? xmlOutMsg.toXml() : SUCCESS;
    }
}
