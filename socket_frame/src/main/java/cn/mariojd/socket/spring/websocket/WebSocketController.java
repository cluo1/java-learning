package cn.mariojd.socket.spring.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @author jdq
 * @date 2018/4/21 15:14
 */
@ServerEndpoint(value = "/socket", encoders = {ServerEncoder.class})
@Component
public class WebSocketController {

    private final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    /**
     * socket连上来要进行处理的事件
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        logger.debug("------ web socket on open");
    }

    /**
     * 所有socket连接过程中的message都会走这里
     * 然后在对应的game房间里进行处理
     *
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.debug("------ web socket on message");
    }

    /**
     * socket关闭会remove掉对应的session
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        logger.debug("------ web socket on close");
    }

    /**
     * socket异常会remove掉对应的session
     *
     * @param session
     */
    @OnError
    public void onError(Throwable e, Session session) {
        logger.debug("------ web socket on socket异常会remove掉对应的session");
    }



}
