package com.tanghong.one.provider

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import com.tanghong.one.R
import com.tanghong.one.app.Constants
import org.jetbrains.anko.toast

/**
 * <pre>
 *     author : hasee
 *     time   : 2018/02/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class MusicWidgetProvider : AppWidgetProvider() {

    /**
     * 每次窗口小部件被更新都调用一次该方法
     */
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        val remoteViews = RemoteViews(context?.packageName, R.layout.app_widget_music)
        val intent = Intent(Constants.action_click)
        val pendingIntent = PendingIntent.getBroadcast(context, R.id.iv_one_icon, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        remoteViews.setOnClickPendingIntent(R.id.iv_one_icon, pendingIntent)

        if (appWidgetIds != null) {
            for (appWidgetId in appWidgetIds) {
                appWidgetManager?.updateAppWidget(appWidgetId, remoteViews)
            }
        }
    }

    /**
     * 接收窗口小部件点击时发送的广播
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        when (intent?.action) {
            Constants.action_click -> {
                context?.toast("click one icon")
            }
        }
    }

    /**
     * 每删除一次窗口小部件就调用一次
     */
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    /**
     * 当最后一个该窗口小部件删除时调用该方法
     */
    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

    /**
     * 当该窗口小部件第一次添加到桌面时调用该方法
     */
    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    /**
     * 当小部件大小改变时
     */
    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }

    /**
     * 当小部件从备份恢复时调用该方法
     */
    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) {
        super.onRestored(context, oldWidgetIds, newWidgetIds)
    }
}