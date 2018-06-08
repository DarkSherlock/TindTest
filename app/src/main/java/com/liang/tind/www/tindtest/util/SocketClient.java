package com.liang.tind.www.tindtest.util;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;

import com.liang.tind.www.tindtest.bean.SocketCloseBean;
import com.liang.tind.www.tindtest.bean.SocketModel;
import com.liang.tind.www.tindtest.bean.SocketOpenBean;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.NotYetConnectedException;
import java.util.ArrayList;
import java.util.List;

public class SocketClient {
    private static final String TAG = "SocketClient";
    private static final int STATUS_CLOSE = 0;
    private static final int STATUS_CONNECT = 1;
    private static final int STATUS_MESSAGE = 2;
    private static final int STATUS_ERROR = 3;

    private static volatile SocketClient singleton = null;
    private Client mClient;

    private List<SocketListener> mSocketListeners;

    private boolean isOpen;

    private SocketClient() {
        mSocketListeners = new ArrayList<>();

    }

    public boolean isOpen() {
        return isOpen;
    }

    public static SocketClient getInstance() {
        if (singleton == null) {
            synchronized (SocketClient.class) {
                if (singleton == null) {
                    singleton = new SocketClient();
                }
            }
        }
        return singleton;
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
//            String message = String.format(Locale.getDefault(), "[%d] %s\n", System.currentTimeMillis(), msg.obj.toString());

            switch (msg.what) {
                case STATUS_CONNECT:
                    isOpen = true;
                    try {
                        SocketModel tind_2593 = SocketModel.getSymbolSocketModel("tind_2593");
                        mClient.send(tind_2593.getJson());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    for (SocketListener listener : mSocketListeners) {
                        listener.onOpen((SocketOpenBean) msg.obj);
                    }
                    break;
                case STATUS_CLOSE:
                    isOpen = false;
                    for (SocketListener listener : mSocketListeners) {
                        listener.onClose((SocketCloseBean) msg.obj);
                    }
                    break;
                case STATUS_MESSAGE:
                    SocketModel socketModel = (SocketModel) msg.obj;
//                    boolean joinGroupSuccess = socketModel.isJoinGroupSuccess();
//                    if (joinGroupSuccess) {
//                        Log.e(TAG, "加入分组成功");
//                    }

                    for (SocketListener listener : mSocketListeners) {
                        listener.onMessage(socketModel);
                    }
                    break;
                case STATUS_ERROR:
                    for (SocketListener listener : mSocketListeners) {
                        listener.onError((Exception) msg.obj);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    public void connectToServer(String address  ) {
        if (isOpen) {
            Log.e(TAG, "connectToServer: socket has connected");
            return;
        }


        Draft draft = new Draft_6455();

        try {
            URI uri = new URI(address);
            mClient = new Client(uri, draft);
            mClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

//        tvStatus.setText(address);
    }


    class Client extends WebSocketClient {

        public Client(URI serverURI) {
            super(serverURI);
        }

        public Client(URI serverUri, Draft draft) {
            super(serverUri, draft);
        }

        @Override
        public void onOpen(ServerHandshake handShakeData) {
            Message msg = new Message();
            msg.what = STATUS_CONNECT;
            msg.obj = new SocketOpenBean(getURI().toString(), handShakeData.getHttpStatusMessage(), handShakeData.getHttpStatus());
            mHandle.sendMessage(msg);
            Log.d(TAG, "onOpen: " + getURI());
        }

        @Override
        public void onMessage(String message) {
            SocketModel socketModel = SocketModel.toSocketModel(message);
            if (socketModel != null && !socketModel.isPulse()) {
                Message msg = new Message();
                msg.what = STATUS_MESSAGE;
                msg.obj = socketModel;
                mHandle.sendMessage(msg);
            }

            Log.d(TAG, "onMessage: " + message);
        }

        @Override
        public void onClose(int code, String reason, boolean remote) {
            Message msg = new Message();
            msg.what = STATUS_CLOSE;
            msg.obj = new SocketCloseBean(code, reason, remote);
            mHandle.sendMessage(msg);
            Log.d(TAG, "onClose: " + getURI());

        }

        @Override
        public void onError(Exception ex) {
            ex.printStackTrace();
            Message msg = new Message();
            msg.what = STATUS_ERROR;
            msg.obj = ex;
            mHandle.sendMessage(msg);
        }
    }

    public void addSocketListener(@NonNull SocketListener listener) {
        if (mSocketListeners != null) {
            mSocketListeners.add(listener);
        }
    }

    public boolean removeSocketListener(@NonNull SocketListener listener) {
        boolean remove = false;
        if (mSocketListeners != null) {
            remove = mSocketListeners.remove(listener);
        }
        return remove;
    }

    public void sendSocketMessage(SocketModel socketModel) {
        if (null != mClient && socketModel != null) {
            try {
                mClient.send(socketModel.getJson());
            } catch (NotYetConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (null != mClient) {
            mClient.close();
        }
    }

  public interface SocketListener {
        void onOpen(SocketOpenBean socketOpenBean);

        void onClose(SocketCloseBean socketCloseBean);

        void onMessage(SocketModel socketModel);

        void onError(Exception e);
    }
}
