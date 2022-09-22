package com.liang.tind.www.tindtest.activty;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;
import com.liang.tind.www.tindtest.bean.SocketCloseBean;
import com.liang.tind.www.tindtest.bean.SocketModel;
import com.liang.tind.www.tindtest.bean.SocketOpenBean;
import com.liang.tind.www.tindtest.util.RxBusFlowable;
import com.liang.tind.www.tindtest.util.SocketClient;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestSocketActivity extends BaseActivity implements SocketClient.SocketListener {

    @BindView(R.id.tv_ip)
    TextView mTvIp;
    @BindView(R.id.et_ip)
    EditText mEtIp;
    @BindView(R.id.tv_port)
    TextView mTvPort;
    @BindView(R.id.et_port)
    EditText mEtPort;
    @BindView(R.id.rbtn_draft10)
    RadioButton mRbtnDraft10;
    @BindView(R.id.rbtn_draft17)
    RadioButton mRbtnDraft17;
    @BindView(R.id.rbtn_draft75)
    RadioButton mRbtnDraft75;
    @BindView(R.id.rbtn_draft76)
    RadioButton mRbtnDraft76;
    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;
    @BindView(R.id.btn_connect)
    Button mBtnConnect;
    @BindView(R.id.btn_close)
    Button mBtnClose;
    @BindView(R.id.listview)
    ListView mListview;
    @BindView(R.id.et_send)
    EditText mEtSend;
    @BindView(R.id.btn_send)
    Button mBtnSend;
    private Subscription mSubscription;
    private SocketClient mSocketClient;

    @OnClick({R.id.btn_connect, R.id.btn_close, R.id.btn_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_connect:
                mSocketClient.connectToServer(MainActivity.SOCKET_SERVER_URL);
                break;
            case R.id.btn_close:
                mSocketClient.close();
                break;
            case R.id.btn_send:
                SocketModel socketModel = new SocketModel();
                socketModel.setSender("student_9527");
                socketModel.setReceiver(SocketModel.RECEIVER_SERVER);
                socketModel.setEvent("notice");
                socketModel.setData(mEtSend.getText().toString().trim());
                mSocketClient.sendSocketMessage(socketModel);
                break;

            default:
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mSocketClient = SocketClient.getInstance();
        mSocketClient.addSocketListener(this);
        System.setProperty("java.net.preferIPv6Addresses", "false");
        System.setProperty("java.net.preferIPv4Stack", "true");
        RxBusFlowable.getInstance().doSubscribe(Integer.class, new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                mSubscription = s;
                RxBusFlowable.getInstance().addSubscription(TestSocketActivity.this, s);
                s.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("Tind", "onNext: " + integer);
                mSubscription.request(1);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_socket;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBusFlowable.getInstance().unSubscribe(this);
        mSocketClient.removeSocketListener(this);
    }

    @Override
    public void onOpen(SocketOpenBean socketOpenBean) {
        Log.e(TAG, "onOpen: " + socketOpenBean);
    }

    @Override
    public void onClose(SocketCloseBean socketCloseBean) {
        Log.e(TAG, "onClose: " + socketCloseBean.toString());
    }

    @Override
    public void onMessage(SocketModel socketModel) {
        Log.e(TAG, "onMessage: " + socketModel.toString());
    }

    @Override
    public void onError(Exception e) {

    }
}
