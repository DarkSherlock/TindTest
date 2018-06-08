package com.liang.tind.www.tindtest.bean;

public class SocketOpenBean {
    private String uri;
    private String status;
    private short code;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public SocketOpenBean(String uri,String status, short code) {
        this.uri = uri;
        this.status = status;
        this.code = code;
    }

    @Override
    public String toString() {
        return "SocketOpenBean{" +
                "uri='" + uri + '\'' +
                ", status='" + status + '\'' +
                ", code=" + code +
                '}';
    }
}
