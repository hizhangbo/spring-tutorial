package top.crazybanana.websocket;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Files;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;

/**
 * @author: Bob
 * @Datetime: 2018-11-25-15:22
 */
@Slf4j
@Component
public class BinaryMessageHandler extends BinaryWebSocketHandler {

    /**
     * 文件下载
     *
     * @param session
     * @param message
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {

        String fileName = null;
        try {
            fileName = URLDecoder.decode(session.getAttributes().get("name").toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            File file = new File("F:\\IDEA\\copy\\mine\\spring-tutorial\\spring-websocket-sample\\src\\main\\resources\\file\\" + fileName);
            byte[] fileSize = new byte[1024 * 1024 * 6];
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(fileSize);
            }
            ByteBuffer byteBuffer = ByteBuffer.wrap(fileSize);
            BinaryMessage binaryMessage = new BinaryMessage(byteBuffer);
            session.sendMessage(binaryMessage);

        } catch (IOException e) {
            log.error("websocket传输 {} 文件失败：{}", fileName, e.getMessage());
        }
    }

    /**
     * 文件上传
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        super.handleBinaryMessage(session, message);
    }
}
