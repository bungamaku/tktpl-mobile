package id.ac.ui.cs.mobileprogramming.bungaamalia.sutaato

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RequestbinService {
    @POST("/")
    fun postWifiNames(@Body wifiScanResult: List<String>): Call<Void>
}
