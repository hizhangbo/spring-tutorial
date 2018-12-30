package top.crazybanana.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.file.Files;

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
            String path = this.getClass().getClassLoader().getResource("file/" + fileName).getPath();
            File file = new File(path);
            byte[] fileSize = Files.readAllBytes(file.toPath());
            try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                fileOutputStream.write(fileSize);
            }
            ByteBuffer byteBuffer = ByteBuffer.wrap(fileSize);
            BinaryMessage binaryMessage = new BinaryMessage(byteBuffer);
            session.sendMessage(binaryMessage);

        } catch (IOException e) {
            log.error("websocket下载 {} 文件失败：{}", fileName, e.toString());
        }
    }
}
