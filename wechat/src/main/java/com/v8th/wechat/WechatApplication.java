package com.v8th.wechat;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.api.WxService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }

    private IService iService = new WxService();

    private void test(){
        WxMessageRouter router = new WxMessageRouter(iService);
        router.rule().msgType(WxConsts.XML_MSG_TEXT).matcher(null).handler(null).end();
    }
}

