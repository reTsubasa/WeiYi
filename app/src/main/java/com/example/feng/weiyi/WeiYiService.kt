package com.example.feng.weiyi

import android.accessibilityservice.AccessibilityService
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo


/**
 * Created by feng on 17-9-29.
 */
class WeiYiService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
    }

    override fun onInterrupt() {
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onAccessibilityEvent(accessibilityEvent: AccessibilityEvent?) {
        when (accessibilityEvent?.eventType) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED -> {
                val className = accessibilityEvent.getClassName().toString()
                if (className == "com.greenline.yihuantong.rapiddiagnose.ReceptionActivity") {
                    val empties = rootInActiveWindow?.findAccessibilityNodeInfosByText("暂无更多患者问诊")
                    if (empties?.size == 1) {
                        val nexts = rootInActiveWindow?.findAccessibilityNodeInfosByText("下一题")
                        nexts!![0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    } else {
                        val answers = rootInActiveWindow?.findAccessibilityNodeInfosByText("抢答")
                        answers!![0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    }
                }
            }
        }
    }
}