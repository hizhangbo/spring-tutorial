package top.crazybanana.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * @author: Bob
 * @Datetime: 2018-11-25-16:07
 */
@Slf4j
@Component
public class ValidInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String url = request.getURI().getPath();
        String name = url.substring(url.lastIndexOf('/') + 1);
        attributes.put("name", name);

        log.info("攔截器開始-來自{}", name);

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {

        log.info("攔截器結束");

        super.afterHandshake(request, response, wsHandler, ex);
    }
}
