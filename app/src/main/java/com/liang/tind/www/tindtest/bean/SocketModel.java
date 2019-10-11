package com.liang.tind.www.tindtest.bean;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class SocketModel {

    public static final String RECEIVER_SYMBOL = "symbol";//指定单一用户
    public static final String RECEIVER_GROUP = "group";//指定组
    public static final String RECEIVER_SERVER = "server";

    /*------------------标记自身事件 客户端在连接上socket给自己标记一个唯一标识符,供通讯使用-----------------------*/
    public static final String RECEIVER_SERVER_SYMBOL = "server.symbol";// 告知服务端,这是标记事件,此处也是固定值
    public static final String EVENT_SYMBOL = "symbol";// 告知服务端,这是标记事件,此处也是固定值
    /*------------加入分组事件 客户端根据业务需要将自身加入到指定的分组内------------------*/
    public static final String RECEIVER_SERVER_GROUP = "server.group";// 接收方一定是服务端,此处是固定值
    public static final String EVENT_JOIN_GROUP = "join_group";//加入分组
    /*---------------------event 事件名称---------------------------*/
    public static final String EVENT_JOIN_GROUP_SUCCESS = "join_group_success";//服务器返回的加入分组成功的Event 不能拿来发送给服务器
    public static final String EVENT_SYMBOL_SUCCESS = "symbol_success";//心跳包事件
    public static final String EVENT_PULSE = "pulse";//心跳包事件

    /**
     * sender : teacher_id
     * receiver : student_id
     * event : class_begin
     * data : hello world
     */

    private String sender;
    private String receiver;
    private String event;
    private String data;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * @return 是否是心跳事件
     */
    public boolean isPulse() {
        return isSomeEvent(EVENT_PULSE);
    }

    /**
     * @return 是否是加入组成功事件
     */
    public boolean isJoinGroupSuccess() {
        return isSomeEvent(EVENT_JOIN_GROUP_SUCCESS);
    }

    /**
     * @return 是否是标记自身成功事件
     */
    public boolean isSymbolSuccess() {
        return isSomeEvent(EVENT_SYMBOL_SUCCESS);
    }

    public boolean isSomeEvent(String event) {
        return this.event.equals(event);
    }

    public static SocketModel getSymbolSocketModel(String dataMsg) {
        SocketModel socketModel = new SocketModel();
        socketModel.setReceiver(SocketModel.RECEIVER_SERVER_SYMBOL);
        socketModel.setSender("student_9527");
        socketModel.setEvent(SocketModel.EVENT_SYMBOL);
        socketModel.setData(dataMsg);
        return socketModel;
    }

    public static SocketModel getJoinGroupSocketModel(String groupName) {
        SocketModel socketModel = new SocketModel();
        socketModel.setReceiver(SocketModel.RECEIVER_SERVER_GROUP);
        socketModel.setSender("student_9527");
        socketModel.setEvent(SocketModel.EVENT_JOIN_GROUP);
        socketModel.setData(groupName);
        return socketModel;
    }

    public static SocketModel toSocketModel(String beanJson) {
        try {
            if (beanJson == null) return null;
            Gson gson = new Gson();
            return gson.fromJson(beanJson, SocketModel.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "SocketModel{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", event='" + event + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
