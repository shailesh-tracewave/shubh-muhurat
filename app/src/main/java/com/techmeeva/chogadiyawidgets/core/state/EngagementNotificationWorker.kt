package com.techmeeva.chogadiyawidgets.core.state

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.Data
import com.techmeeva.chogadiyawidgets.MainActivity
import com.techmeeva.chogadiyawidgets.R
import java.util.concurrent.TimeUnit

class EngagementNotificationWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val title = inputData.getString("title") ?: "Shubh Muhurat"
        val body = inputData.getString("body") ?: "A gentle reminder: your daily Choghadiya path is ready."
        val notificationId = inputData.getInt("id", 0)

        showNotification(context, title, body, notificationId)
        return Result.success()
    }

    private fun showNotification(context: Context, title: String, body: String, notificationId: Int) {
        val channelId = "engagement_channel"
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Daily Reminders",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher) // Use app icon
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    companion object {
        private const val SETTINGS_PREFS = "notification_settings"
        private const val DAILY_REMINDERS_ENABLED = "daily_reminders_enabled"
        private const val REMINDER_TAG = "engagement_reminders"

        fun dailyRemindersEnabled(context: Context): Boolean {
            return context.getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE)
                .getBoolean(DAILY_REMINDERS_ENABLED, false)
        }

        fun setDailyRemindersEnabled(context: Context, enabled: Boolean) {
            context.getSharedPreferences(SETTINGS_PREFS, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(DAILY_REMINDERS_ENABLED, enabled)
                .apply()

            if (enabled) {
                scheduleEngagementReminders(context)
            } else {
                cancelEngagementReminders(context)
            }
        }

        fun cancelEngagementReminders(context: Context) {
            WorkManager.getInstance(context).cancelAllWorkByTag(REMINDER_TAG)
        }

        fun scheduleEngagementReminders(context: Context) {
            val workManager = WorkManager.getInstance(context)
            workManager.cancelAllWorkByTag(REMINDER_TAG)

            val reminders = listOf(
                Pair(7.0, "Plan your week with today’s auspicious Choghadiya timings."),
                Pair(14.0, "A gentle reminder: your daily Choghadiya path is ready."),
                Pair(21.0, "Check the solar and lunar rhythm before important work today."),
                Pair(30.0, "A new rhythm begins. See today’s best timing window."),
                Pair(45.0, "Your auspicious timing guide is waiting for today’s flow."),
                Pair(60.0, "Pause for a moment and align your day with Shubh Muhurat."),
                Pair(75.0, "Fresh Choghadiya, sunrise, sunset, and moon timing are ready."),
                Pair(90.0, "Return to your daily rhythm with today’s Choghadiya guidance.")
            )

            reminders.forEachIndexed { index, reminder ->
                val data = Data.Builder()
                    .putString("title", "Shubh Muhurat")
                    .putString("body", reminder.second)
                    .putInt("id", index + 100)
                    .build()

                val request = OneTimeWorkRequestBuilder<EngagementNotificationWorker>()
                    .setInitialDelay(reminder.first.toLong(), TimeUnit.DAYS)
                    .setInputData(data)
                    .addTag(REMINDER_TAG)
                    .build()

                workManager.enqueue(request)
            }
        }
    }
}
