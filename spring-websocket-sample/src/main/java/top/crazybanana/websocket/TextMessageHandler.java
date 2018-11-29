package top.crazybanana.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.util.Strings;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Bob
 * @Datetime: 2018-11-25-15:20
 */
@Component
public class TextMessageHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> clients = new HashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String name = (String) session.getAttributes().get("name");
        if (!Strings.isBlank(name)) {
            clients.put(name, session);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String name = (String) session.getAttributes().get("name");
        if(clients.keySet().contains(name)){
            clients.remove(name);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String content = new String(message.asBytes());

        JsonParser jsonParser = new JsonParser();
        JsonObject obj = jsonParser.parse(content).getAsJsonObject();

        String fromUser = (String) session.getAttributes().get("name");

        String toUser = obj.get("user").getAsString();
        String text = obj.get("text").getAsString();

        TextMessage textMessage = new TextMessage(fromUser + ":" + text);
        sendMessage(toUser, textMessage);
    }

    public void sendMessage(String toUser, TextMessage message) {
        WebSocketSession session = clients.get(toUser);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
