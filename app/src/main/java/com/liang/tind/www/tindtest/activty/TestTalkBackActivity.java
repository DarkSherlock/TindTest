package com.liang.tind.www.tindtest.activty;

import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liang.tind.www.tindtest.R;
import com.liang.tind.www.tindtest.base.BaseActivity;

/**
 * ceshi
 * created by liangtiande
 * date 2018/8/27
 */
public class TestTalkBackActivity extends BaseActivity {
    private static final int POPULATING_ACCESSIBILITY_EVENT_TYPES =
            AccessibilityEvent.TYPE_VIEW_CLICKED
                    | AccessibilityEvent.TYPE_VIEW_LONG_CLICKED
                    | AccessibilityEvent.TYPE_VIEW_SELECTED
                    | AccessibilityEvent.TYPE_VIEW_FOCUSED
                    | AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
                    | AccessibilityEvent.TYPE_VIEW_HOVER_ENTER
                    | AccessibilityEvent.TYPE_VIEW_HOVER_EXIT
                    | AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED
                    | AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED
                    | AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED
                    | AccessibilityEvent.TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_talkback;
    }

    @Override
    protected void init() {

        final LinearLayout llRoot= findViewById(R.id.ll_root);
        final TextView tv5= findViewById(R.id.tv5);
        final TextView tv2= findViewById(R.id.tv2);
        final TextView tv3= findViewById(R.id.tv3);
        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: ");
            }
        });

//        llRoot.setAccessibilityDelegate(new View.AccessibilityDelegate(){
//
//            @Override
//            public void sendAccessibilityEventUnchecked(View host, AccessibilityEvent event) {
//                super.sendAccessibilityEventUnchecked(host,event);
////                Log.e(TAG, "event2:"+event);
////                if (!llRoot.isShown()) {
////                    return;
////                }
////                Log.e(TAG, "sendAccessibilityEventUnchecked:=============> ");
////                llRoot.onInitializeAccessibilityEvent(event);
////                // Only a subset of accessibility events populates text content.
////
////                if ((event.getEventType() & POPULATING_ACCESSIBILITY_EVENT_TYPES) != 0) {
////                    llRoot.dispatchPopulateAccessibilityEvent(event);
////                    Log.e(TAG, "event: "+event);
////                }
////                // In the beginning we called #isShown(), so we know that getParent() is not null.
////                ViewParent parent = llRoot.getParent();
////                if (parent != null) {
////                    llRoot.getParent().requestSendAccessibilityEvent(llRoot, event);
////                    Log.e(TAG, "event2: "+event);
////                }
//            }
//
//            @Override
//            public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
//                super.onInitializeAccessibilityEvent(host, event);
//                Log.e("Tind1", "event3: "+event);
//            }
//
//            @Override
//            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
//                super.onInitializeAccessibilityNodeInfo(host, info);
//                Log.e("Tind2", "info: "+info);
//            }
//
//            @Override
//            public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
////                super.onPopulateAccessibilityEvent(host, event);
//
//                Log.e("Tind3", "event4:"+event);
//            }
//
//            @Override
//            public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
////                boolean handled = false;
////
////                // Let our children have a shot in populating the event.
////                final int childCount = llRoot.getChildCount();
////                for (int i = 0; i < childCount; i++) {
////                    View child = llRoot.getChildAt(i);
////                    if ((child.getVisibility() == View.VISIBLE)) {
////                        handled = child.dispatchPopulateAccessibilityEvent(event);
////                        Log.e(TAG, "handled: "+handled);
////                        if (handled) {
////                            return true;
////                        }
////                    }
////                }
////                return false;
//                Log.e("Tind4", "event: "+event);
////                return super.dispatchPopulateAccessibilityEvent(host,event);
//                    super.dispatchPopulateAccessibilityEvent(host,event);
//                return true;
//            }
//        });

        tv2.setAccessibilityDelegate(new View.AccessibilityDelegate(){
            @Override
            public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onInitializeAccessibilityEvent(host, event);
                Log.e("Tv1", "event3: "+event);
            }

            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                Log.e("Tv2", "info: "+info);
                info.setText("添加");
            }

            @Override
            public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onPopulateAccessibilityEvent(host, event);

                Log.e("Tv3", "event4:"+event);
            }

            @Override
            public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {

                return super.dispatchPopulateAccessibilityEvent(host,event);
            }
        });

        tv3.setAccessibilityDelegate(new View.AccessibilityDelegate(){
            @Override
            public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onInitializeAccessibilityEvent(host, event);
                Log.e("tx1", "event3: "+event);
            }

            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                Log.e("tx2", "info: "+info);
            }

            @Override
            public void onPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onPopulateAccessibilityEvent(host, event);
                Log.e("tx3", "event4:"+event);
            }

            @Override
            public boolean dispatchPopulateAccessibilityEvent(View host, AccessibilityEvent event) {
                Log.e("tx4", "event: "+event);
                return true;
            }
        });
    }
}
