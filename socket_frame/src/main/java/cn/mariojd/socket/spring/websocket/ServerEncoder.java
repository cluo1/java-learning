package cn.mariojd.socket.spring.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author jdq
 * @date 2018/4/21 15:14
 */
public class ServerEncoder implements Encoder.Text<Object> {

    @Override
    public void init(EndpointConfig arg0) {
        //  Auto-generated method stub
    }

    @Override
    public String encode(Object o) throws EncodeException {
        System.out.println(o);
        return "";
    }

    @Override
    public void destroy() {
        //  Auto-generated method stub
    }

}
