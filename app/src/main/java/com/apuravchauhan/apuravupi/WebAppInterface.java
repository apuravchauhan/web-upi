package com.apuravchauhan.apuravupi;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.util.List;

/**
 * @author apuravchauhan
 */
public class WebAppInterface {
    Context mContext;
    String payeeAddress = "9876470996@paytm";
    String payeeName = "Apurav Chauhan";
    String transactionNote = "Buy me a beer!";
    String amount = "1";
    String currencyUnit = "INR";

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public String upiHandlers(){
        Uri uri = Uri.parse("upi://pay?pa="+payeeAddress+"&pn="+payeeName+"&tn="+transactionNote+
                "&am="+amount+"&cu="+currencyUnit);
        Log.d("Apurav UPI deeplink", "onClick: uri: "+uri);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        List<ResolveInfo> activities =  mContext.getPackageManager().queryIntentActivities(intent,0);
        StringBuilder apps = new StringBuilder();
        boolean first = true;
        for (ResolveInfo act : activities) {
            ActivityInfo ai = act.activityInfo;
            Log.d("###",""+" :: "+ai.applicationInfo.packageName+" | "+ai.name);
            if(first){
                apps.append(ai.applicationInfo.packageName+"|"+ai.name);
                first=!first;
            }else{
                apps.append(",").append(ai.applicationInfo.packageName+"|"+ai.name);

            }
        }

        return  apps.toString();
    }

    @JavascriptInterface
    public void openUPIHandler(String upiPackage,String activity){
        Uri uri = Uri.parse("upi://pay?pa="+payeeAddress+"&pn="+payeeName+"&tn="+transactionNote+
                "&am="+amount+"&cu="+currencyUnit);
        Log.d("Apurav open UPI handler", "onClick: uri: "+uri);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setClassName(upiPackage,activity);
        mContext.startActivity(intent);

    }
}