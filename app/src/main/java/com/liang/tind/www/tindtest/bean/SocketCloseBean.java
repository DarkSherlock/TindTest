package com.liang.tind.www.tindtest.bean;

public class SocketCloseBean {
    private int code;
    private String reason;
    private boolean remote;

    public SocketCloseBean(int code, String reason, boolean remote) {
        this.code = code;
        this.reason = reason;
        this.remote = remote;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    @Override
    public String toString() {
        return "SocketCloseBean{" +
                "code=" + code +
                ", reason='" + reason + '\'' +
                ", remote=" + remote +
                '}';
    }
}
