package com.liang.tind.www.tindtest.audio;

/**
 * @author lonnie.liang
 * @date 2021/04/18 21:14
 */
interface AudioDelegate {
    void didFinishPlaying(boolean successfully);

    void didDecodeErrorOccur(long code, String domain);
}
