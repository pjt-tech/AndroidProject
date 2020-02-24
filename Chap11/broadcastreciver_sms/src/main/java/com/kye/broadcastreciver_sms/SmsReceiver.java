package com.kye.broadcastreciver_sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.sql.Date;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("SMS Reciver","onReceive() 호출됨");
        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);
        if(messages!=null && messages.length>0){
            String sender = messages[0].getOriginatingAddress(); //발신번호
            Log.d("SMS Reciver","SMS Sender"+ sender);

            String contents = messages[0].getDisplayMessageBody(); //발신메시지
            Log.d("SMS Reciver","SMS contents"+ contents);

            Date recevedDate = new Date(messages[0].getTimestampMillis()); //수신시간
            Log.d("SMS Reciver","SMS recevedDate"+ recevedDate);
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] object = (Object[])bundle.get("pdus");
        //sms 데이터를 처리하는 SMTP가 있는데 그 안에 pdus라는 이름으로 sms 정보들이 들어가 있음
        //실제 메시지는 object[]에 pdus 형식으로 저장되어 있음
        SmsMessage[] messages = new SmsMessage[object.length];
        //sms를 받아올 SmsMessage 배열을 만듬
        for(int i= 0; i<object.length; i++){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) object[i],format);
                //각 메시지를 추출
            }else {
                messages[i] = SmsMessage.createFromPdu((byte[])object[i]);
            }
        }
        return  messages;
    }
}
