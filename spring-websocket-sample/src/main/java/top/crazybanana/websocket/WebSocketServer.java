package top.crazybanana.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author: Bob
 * @Datetime: 2018-11-25-14:17
 */
@Slf4j
@Configuration
@EnableWebSocket
public class WebSocketServer implements WebSocketConfigurer {

    private TextMessageHandler textMessageHandler;

    private ValidInterceptor validInterceptor;

    @Autowired
    public WebSocketServer(TextMessageHandler textMessageHandler, ValidInterceptor validInterceptor){
        this.textMessageHandler = textMessageHandler;
        this.validInterceptor = validInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(textMessageHandler, "chat/*")
//                .addHandler(binaryMessageHandler, "download/*")
                .addInterceptors(validInterceptor);
    }
}
