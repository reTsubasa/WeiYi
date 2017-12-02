package com.example.feng.weiyi

import android.accessibilityservice.AccessibilityService
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

    override fun onDestroy() {
        super.onDestroy()
        Log.e("milan", "onDestroy")
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onAccessibilityEvent(accessibilityEvent: AccessibilityEvent?) {
        when (accessibilityEvent?.eventType) {
            AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED,
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
                if (accessibilityEvent.className == "com.greenline.yihuantong.message.gpconsultation.ConsultListActivity") {
                    val nextNodeInfos = rootInActiveWindow?.findAccessibilityNodeInfosByViewId("com.greenline.yihuantong:id/gh_base_consult_list_special_layout")
                    if (nextNodeInfos!!.size > 0) {
                        nextNodeInfos[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    }
                    Log.e("milan", "问诊")
                } else if (accessibilityEvent.className == "com.greenline.yihuantong.consult.specialclinic.SpecialClinicListActivity") {
                    val handleNodeInfos = rootInActiveWindow?.findAccessibilityNodeInfosByViewId("com.greenline.yihuantong:id/tv_accept_order")
                    if (handleNodeInfos!!.size > 0) {
                        handleNodeInfos[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        val service = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                        service.vibrate(400)
                    } else {
                        val backNodeInfos = rootInActiveWindow?.findAccessibilityNodeInfosByViewId("com.greenline.yihuantong:id/actionbar_home_btn")
                        Log.e("milan", "size: " + backNodeInfos!!.size)
                        if (backNodeInfos.size > 0) {
                            backNodeInfos[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                        }
                    }
                    Log.e("milan", "候诊室")
                }
            }
        }
    }
}