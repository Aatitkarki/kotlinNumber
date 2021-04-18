package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_PHONE_NUMBER
import android.telephony.TelephonyManager
import android.util.Log

class AnswerCallBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(arg0: Context, arg1: Intent) {
        if (arg1.getAction().equals("android.intent.action.PHONE_STATE"))
        {
            val state = arg1.getStringExtra(TelephonyManager.EXTRA_STATE)
            if (state == TelephonyManager.EXTRA_STATE_OFFHOOK)
            {
                Log.d(TAG, "Inside Extra state off hook")
                val number = arg1.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                Log.e(TAG, "outgoing number : " + number)
            }
            else if (state == TelephonyManager.EXTRA_STATE_RINGING)
            {
                Log.e(TAG, "Inside EXTRA_STATE_RINGING")
                val number = arg1.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                Log.e(TAG, "incoming number : " + number)
            }
            else if (state == TelephonyManager.EXTRA_STATE_IDLE)
            {
                Log.d(TAG, "Inside EXTRA_STATE_IDLE")
            }
        }
    }
}