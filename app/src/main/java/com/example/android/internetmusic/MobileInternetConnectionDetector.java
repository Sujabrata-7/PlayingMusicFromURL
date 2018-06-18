package com.example.android.internetmusic;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MobileInternetConnectionDetector {
    private Context _context;

    public MobileInternetConnectionDetector(Context context){
        this._context=context;
    }

    public Boolean checkMobileInternetConn(){
        ConnectivityManager connectivity=(ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivity!=null){

                NetworkInfo info=connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                    if(info!=null){
                            if(info.isConnected()){
                                return true;
                            }
                    }
            }
            return false;
    }
}
