package com.csdn.demospringboot.websocket;

import com.csdn.demospringboot.dao.UserDao;
import com.csdn.demospringboot.dto.Message;
import com.csdn.demospringboot.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(prefix = "netty-websocket")
@Component
public class WebSocketServer  {

    private static Map<String,Session> userMap=new ConcurrentHashMap<>();
    private static  ObjectMapper objectMapper=new ObjectMapper();
    @Autowired
    private UserDao userDao;
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers) throws IOException {

    }

    @OnClose
    public void onClose(Session session) throws IOException {
        Iterator<String> keySet=userMap.keySet().iterator();
        String customerid=null;
        while(keySet.hasNext()){
            String result=keySet.next();

            if((customerid=result.split("-")[0]).equals(session.id().toString())){
              userMap.remove(result);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void OnMessage(Session session, String message) throws IOException {
       Message s=objectMapper.readValue(message,Message.class);
       //主管发消息给客服
           userMap.put(session.id()+"-"+s.getFrom()+"-"+s.getType(),session);//主管id
           Iterator<String> keySet=userMap.keySet().iterator();
           String customerid=null;
           while(keySet.hasNext()){
               String result1=keySet.next();
               String []result=result1.split("-");
               if((customerid=result[1]).equals(s.getTo())&&!s.getType().equals(result[2])){
                   Session sessionc=userMap.get(result1);
                   if(sessionc!=null&&sessionc.isOpen()){
                       sessionc.sendText(s.getText());
                   }
               }
           }





    }

    @OnBinary
    public void OnBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }

}
