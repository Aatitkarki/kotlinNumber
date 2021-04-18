package com.example.myapplication

import android.provider.CallLog
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import java.util.logging.Handler

private class PhoneCallListener: PhoneStateListener() {
    private val isPhoneCalling = false
    override fun onCallStateChanged(state:Int, incomingNumber:String) {
        if (TelephonyManager.CALL_STATE_RINGING === state)
        {
            // phone ringing
            val LOG_TAG="Log";
            Log.i(LOG_TAG, "RINGING, number: " + incomingNumber)
        }
        if (TelephonyManager.CALL_STATE_OFFHOOK === state)
        {
            val LOG_TAG="Log";
            // active
            Log.i(LOG_TAG, "OFFHOOK")
            isPhoneCalling = true
        }
        if (TelephonyManager.CALL_STATE_IDLE === state)
        {
            val LOG_TAG="Log";
            // run when class initial and phone call ended, need detect flag
            // from CALL_STATE_OFFHOOK
            Log.i(LOG_TAG, "IDLE number")
            if (isPhoneCalling)
            {
                val handler = Handler()
                //Put in delay because call log is not updated immediately when state changed
                // The dialler takes a little bit of time to write to it 500ms seems to be enough
                handler.postDelayed(object:Runnable {
                    public override fun run() {
                        // get start of cursor
                        Log.i("CallLogDetailsActivity", "Getting Log activity...")
                        val projection = arrayOf<String>(CallLog.Calls.NUMBER)
                        val cur = getContentResolver().query(Calls.CONTENT_URI, projection, null, null, Calls.DATE + " desc")
                        cur.moveToFirst()
                        val lastCallnumber = cur.getString(0)
                    }
                }, 500)
                isPhoneCalling = false
            }
        }
    }
}