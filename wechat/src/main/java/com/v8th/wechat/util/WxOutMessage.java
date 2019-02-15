package com.v8th.wechat.util;

import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.bean.outxmlbuilder.*;

public class WxOutMessage extends WxXmlOutMessage {

    private static void setFormTo(BaseBuilder builder, WxXmlMessage wxMessage) {
        builder.fromUser(wxMessage.getToUserName());
        builder.toUser(wxMessage.getFromUserName());
    }

    public static TextBuilder TEXT(WxXmlMessage wxMessage) {
        TextBuilder builder = new TextBuilder();
        setFormTo(builder, wxMessage);
        return builder;
    }

    public static NewsBuilder NEWS(WxXmlMessage wxMessage) {
        NewsBuilder builder = new NewsBuilder();
        setFormTo(builder, wxMessage);
        return builder;
    }



    /*TODO
    public static ImageBuilder IMAGE() {
        return new ImageBuilder();
    }

    public static VoiceBuilder VOICE() {
        return new VoiceBuilder();
    }

    public static VideoBuilder VIDEO() {
        return new VideoBuilder();
    }

    public static MusicBuilder MUSIC(){
        return new MusicBuilder();
    }
    */
}
