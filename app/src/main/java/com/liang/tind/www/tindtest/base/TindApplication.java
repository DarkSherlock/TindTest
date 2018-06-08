package com.liang.tind.www.tindtest.base;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class TindApplication extends TinkerApplication {

    public TindApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, MyApplicationLike.class.getCanonicalName(),
                "com.tencent.tinker.loader.TinkerLoader", false);
    }

}
