package com.example.feng.weiyi

import android.accessibilityservice.AccessibilityService
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Vibrator
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo


/**
 * Created by feng on 17-9-29.
 */
class WeiYiService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        Log.e("milan", "onServiceConnected")
    }

    override fun onInterrupt() {
        Log.e("milan", "onInterrupt")
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onAccessibilityEvent(accessibilityEvent: AccessibilityEvent?) {
        when (accessibilityEvent?.eventType) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED,
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
                if (accessibilityEvent.className == "com.greenline.yihuantong.consult.specialclinic.SpecialConsultDetailActivity") {
                    Log.e("milan", "consult")
                } else if (accessibilityEvent.className == "com.greenline.yihuantong.message.gpconsultation.GPConsultationListActivity") {
                    val nextNodeInfos = rootInActiveWindow?.findAccessibilityNodeInfosByViewId("com.greenline.yihuantong:id/special_layout")
                    nextNodeInfos!![0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                } else if (accessibilityEvent.className == "com.greenline.yihuantong.consult.specialclinic.SpecialClinicListActivity") {
                    val handleNodeInfos = rootInActiveWindow?.findAccessibilityNodeInfosByViewId("com.greenline.yihuantong:id/tv_accept_order")
                    if (handleNodeInfos!!.size > 0) {
                        handleNodeInfos[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        val service = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        service.vibrate(400)
                    } else {
                        val backNodeInfos = rootInActiveWindow?.findAccessibilityNodeInfosByViewId("com.greenline.yihuantong:id/actionbar_home_btn")
                        backNodeInfos!![0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    }
                }
            }
        }
    }
}