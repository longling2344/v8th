package com.v8th.wechat.bean;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitBean {

    /**
     * @return IService 实现类WxService
     */
    @Bean
    public IService iService(){
        return new WxService();
    }
}
