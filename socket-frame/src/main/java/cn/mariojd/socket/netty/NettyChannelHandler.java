package cn.mariojd.socket.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jdq
 * @date 2018/4/3 11:15
 */
public class NettyChannelHandler extends SimpleChannelInboundHandler<Object> {

    private final Logger logger = LoggerFactory.getLogger(NettyChannelHandler.class);

    private WebSocketServerHandshaker handshake;

    private List<Channel> channelList = new ArrayList<>();

    private AtomicInteger integer = new AtomicInteger(0);

    /**
     * 连接创建的时候调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.debug("------ web socket on active");
    }

    /**
     * 出现异常的时候调用
     *
     * @param ctx
     * @param e
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        ctx.close();
        e.printStackTrace();
    }

    /**
     * 连接断开的时候调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }

    /**
     * 发送数据结束之后调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 服务端处理客户端的WebSocket请求
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof WebSocketFrame) {
            //处理WebSocket业务请求
            handWebSocketFrame(ctx, (WebSocketFrame) msg);
        } else if (msg instanceof FullHttpRequest) {
            //处理Http握手请求
            handHttpRequest(ctx, (FullHttpRequest) msg);
        }
    }

    /**
     * 处理客户端与服务端之前的WebSocket业务
     *
     * @param ctx
     * @param frame
     */
    private void handWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        Channel channel = ctx.channel();

        if (frame instanceof TextWebSocketFrame) {
            //文本消息
            channelList.forEach(channel1 -> {
                int andIncrement = integer.getAndIncrement();
                TextWebSocketFrame tws = new TextWebSocketFrame(String.valueOf(andIncrement));
                channel.writeAndFlush(tws);
            });
            //System.out.println(channelList.size());
//            String message = ((TextWebSocketFrame) frame).text() + "....";
//            TextWebSocketFrame tws = new TextWebSocketFrame(message);
//            channel.writeAndFlush(tws);
        } else if (frame instanceof CloseWebSocketFrame) {
            //关闭WebSocket消息
            handshake.close(channel, (CloseWebSocketFrame) frame.retain());
        } else if (frame instanceof PingWebSocketFrame) {
            //心跳Ping消息
            channel.writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
        } else if (frame instanceof BinaryWebSocketFrame) {
            //二进制消息
            logger.error("------- 不支持二进制消息");
        }
    }

    /**
     * 处理客户端向服务端发起http握手请求的业务,进行握手响应返回
     *
     * @param ctx
     * @param req
     */
    private void handHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        Channel channel = ctx.channel();

        if (!req.decoderResult().isSuccess() ||
                !(HttpHeaderValues.WEBSOCKET.toString().equals(req.headers().get(HttpHeaders.UPGRADE)))) {
            //不是web socket连接，返回错误Response
            DefaultFullHttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
            channel.writeAndFlush(res);
            channel.close();
        } else {
            WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                    "ws://" + req.headers().get(HttpHeaders.HOST), null, false);
            handshake = wsFactory.newHandshaker(req);

            if (handshake == null) {
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(channel);
            } else {
                handshake.handshake(channel, req);

                //ctx.attr(AttributeKey.valueOf("id")).set(1);
                //ctx.attr(AttributeKey.valueOf("id").get();

                /*QueryStringDecoder queryStringDecoder = new QueryStringDecoder(req.uri());
                Map<String, List<String>> parameters = queryStringDecoder.parameters();*/
                channelList.add(channel);
                TextWebSocketFrame tws = new TextWebSocketFrame("shake success");
                channel.writeAndFlush(tws);
            }
        }
    }

}
