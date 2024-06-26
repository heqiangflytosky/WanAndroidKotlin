package com.android.hq.wanandroidkotlin.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Environment
import android.widget.TextView
import com.android.hq.wanandroidkotlin.R
//import com.mikepenz.iconics.IconicsDrawable
//import com.mikepenz.iconics.typeface.IIcon
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*



object AppUtils {
    const val INTENT_ITEM_INFO = "intent_item_info"
    private var mAppContext: Context? = null
    private val sDataFormatZ = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private val sDataFormat6S = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private val sDataFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun init(context: Context) {
        mAppContext = context
        //sDataFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    fun getAppContent():Context?{
        return mAppContext
    }

    fun getCacheDir(): String {
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState() || !Environment.isExternalStorageRemovable()) {
            val cacheFile = mAppContext!!.externalCacheDir
            if (null != cacheFile) {
                return cacheFile.path
            }
        }
        return mAppContext!!.cacheDir.path
    }

    /**
     * 网络可访问
     */
    fun isConnected(): Boolean {
        val cm = mAppContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } else {
            // 只是判断有网络连接
            cm.activeNetworkInfo?.isConnected == true
        }
    }

    fun convertTimestamp(timestampMilli: Long): String {
        // 创建一个SimpleDateFormat对象，指定目标日期格式
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        // 创建一个Date对象，将时间戳转换为毫秒级别
        val date = Date(timestampMilli)
        // 使用SimpleDateFormat对象格式化Date对象为字符串
        return sdf.format(date)
    }
    fun formatPublishedTime(time: String): String? {
        var c: Calendar? = null
        try {
            if (time.endsWith("Z")) {
                sDataFormatZ.parse(time)
                c = sDataFormatZ.getCalendar()
            } else if (time.endsWith("SSSSSS")) {
                sDataFormat6S.parse(time)
                c = sDataFormat6S.getCalendar()
            } else {
                sDataFormat.parse(time)
                c = sDataFormat.getCalendar()
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            return null
        }

        if (c != null) {
            val year = c!!.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH) + 1
            val day = c.get(Calendar.DAY_OF_MONTH)
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)
            val second = c.get(Calendar.SECOND)

            //获取当前时间
            //            Date date = new Date();
            //            sDataFormat.format(date);
            c.timeInMillis = System.currentTimeMillis()
            val cur_year = c.get(Calendar.YEAR)
            val cur_month = c.get(Calendar.MONTH) + 1
            val cur_day = c.get(Calendar.DAY_OF_MONTH)
            val cur_hour = c.get(Calendar.HOUR_OF_DAY)
            val cur_minute = c.get(Calendar.MINUTE)
            val cur_second = c.get(Calendar.SECOND)

            if (year < cur_year) {
                return (cur_year - year).toString() + mAppContext?.resources?.getString(R.string.text_years_ago)
            } else if (month < cur_month) {
                return (cur_month - month).toString() + mAppContext?.resources?.getString(R.string.text_months_ago)
            } else if (day < cur_day) {
                return (cur_day - day).toString() + mAppContext?.resources?.getString(R.string.text_days_ago)
            } else if (hour < cur_hour) {
                return (cur_hour - hour).toString() + mAppContext?.resources?.getString(R.string.text_hours_ago)
            } else if (minute < cur_minute) {
                return (cur_minute - minute).toString() + mAppContext?.resources?.getString(R.string.text_minutes_ago)
            } else if (second < cur_second) {
                return (cur_second - second).toString() + mAppContext?.resources?.getString(R.string.text_seconds_ago)
            }
        }
        return null
    }

//    fun setTextViewLeftDrawableForHeader(textView: TextView, icon: IIcon) {
//        val drawable = IconicsDrawable(mAppContext)
//            .icon(icon)
//            .color(mAppContext?.resources?.getColor(R.color.theme_primary)?:0)
//            .sizeDp(14)
//        textView.setCompoundDrawables(drawable, null, null, null)
//    }

//    fun startArticleDetailActivity(context: Context, data:GankDetailData) {
//        var intent = Intent(context, ArticleDetailActivity::class.java)
//        intent.putExtra(INTENT_ITEM_INFO, data)
//        context.startActivity(intent)
//        //MyClass.HH.newInstance()
//    }

//    class MyClass {
//        companion object HH{
//            fun newInstance():MyClass {
//
//            }
//        }
//    }
}