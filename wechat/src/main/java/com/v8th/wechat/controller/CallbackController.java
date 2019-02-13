package com.v8th.wechat.controller;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.util.xml.XStreamTransformer;
import com.v8th.wechat.handler.OpenIdHandler;
import com.v8th.wechat.matcher.OpenIdMatcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

    @Autowired
    public CallbackController(OpenIdMatcher openIdMatcher, OpenIdHandler openIdHandler) {
        this.openIdMatcher = openIdMatcher;
        this.openIdHandler = openIdHandler;
    }

    @GetMapping
    public String check(String signature, String timestamp, String nonce, String echostr) {
        if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
            return echostr;
        }
        return CHKFAIL;
    }

    @PostMapping
    public String handle(HttpServletRequest request) throws IOException {
        request.setCharacterEncoding(UTF8);
        // 创建一个路由器
        WxMessageRouter router = new WxMessageRouter(iService);

        WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
        router.rule().msgType(WxConsts.XML_MSG_TEXT).matcher(openIdMatcher).handler(openIdHandler).end();

        // 把消息传递给路由器进行处理
        WxXmlOutMessage xmlOutMsg = router.route(wx);
        if (xmlOutMsg != null)
            return xmlOutMsg.toXml();
        return SUCCESS;
    }

}
