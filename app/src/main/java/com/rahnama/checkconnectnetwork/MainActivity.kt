package com.rahnama.checkconnectnetwork

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.NetworkInfo

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*********************/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if (isNetworkConnected()) {
                Toast.makeText(this, "connected", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "check connection to internet", Toast.LENGTH_LONG).show()

            }
        }else{
            val ConnectionManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = ConnectionManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected == true) {
                Toast.makeText(this@MainActivity, "Network Available", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity, "Network Not Available", Toast.LENGTH_LONG).show()
            }
        }
        /****************************/
    }
/**************************************************/

    @RequiresApi(Build.VERSION_CODES.M)
    private fun isNetworkConnected(): Boolean {
    val connectivityManager =getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
    }
}