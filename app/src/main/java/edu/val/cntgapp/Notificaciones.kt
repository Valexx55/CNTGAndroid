package edu.`val`.cntgapp

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import edu.`val`.cntgapp.perros.PerrosActivity

object Notificaciones {

    const val ID_CANAL = "CANAL 1 CNTGAPP"
    const val NOMBRE_CANAL = "CANAL CNTGAPP"

    fun lanzarNotificacion (context: Context):Unit
    {
        //accedemos al servicio de android NotificationManager, el encargado degestionar las notificaciones
         val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //var notificactionChanel = crearCanalNotificacion (context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) //si estoy en versión igual o superrio a Oreo - 8
        {
            var notificactionChanel = crearCanalNotificacion (context)
            notificationManager.createNotificationChannel(notificactionChanel!!)//le pido lo cree y ya esta, y no hace falta, por dentro no lo crea. no es necesario controlar la existencia del canal
        }

        var nb = NotificationCompat.Builder(context, ID_CANAL)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.baseline_menu_24)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.emoticono_risa))
            .setContentTitle("BUENOS DÍAS")
            .setSubText("aviso")
            .setContentText("Vamos a ver fotos de perros")
            .setAutoCancel(true)//es para que cuando toque la noti, desaparezca
            .setDefaults(Notification.DEFAULT_ALL)

        //si estoy en versión inferior a 8, le tengo asignar el sonido a la notificaciómn
        //si estoy en versión superior, el sonido lo toma del canal

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
        {
            nb.setSound(Uri.parse("android.resource://" + context.packageName + "/" + R.raw.snd_noti));
        }

        val intentActivityPerros = Intent(context, PerrosActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(context, 100, intentActivityPerros,PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE )

        nb.setContentIntent(pendingIntent)
        val notificacion = nb.build()

        notificationManager.notify(500, notificacion) //lanzar la notificación

    }

    @RequiresApi(Build.VERSION_CODES.O)//DESPARECE EL WARNING Y ADEMÁS, AL LLAMANTE, LE OBLIGA A GESTIONAR QUE ESTOY EN LA VERSIÓN ADECUADA (8 O SUPERIOR)
    //@TargetApi(Build.VERSION_CODES.O)//DESPARECE EL WARNING PERO, AL LLAMANTE, NO LE OBLIGA A GESTIONAR QUE ESTOY EN LA VERSIÓN ADECUADA (8 O SUPERIOR)
    private fun crearCanalNotificacion(context: Context): NotificationChannel? {
        var notificationChannel:NotificationChannel? = null

            notificationChannel = NotificationChannel(ID_CANAL, NOMBRE_CANAL, NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern = longArrayOf(
                500,
                500,
                500,
                500,
                500,
                500,
                500,
                500
            )
            notificationChannel.lightColor = context.getColor(R.color.fucsia)

        val audioAttributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()

        notificationChannel.setSound(
            Uri.parse("android.resource://" + context.packageName + "/" + R.raw.snd_noti),
            audioAttributes
        )


        return notificationChannel

    }
}