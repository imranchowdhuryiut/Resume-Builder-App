package com.example.resumebuilder.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import java.text.SimpleDateFormat
import java.util.*
import android.provider.MediaStore
import android.provider.Settings
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.resumebuilder.R


/**
 * Created by Imran Chowdhury on 2/19/2022.
 */

val dateFormat by lazy { SimpleDateFormat("d MMMM, yyyy", Locale.getDefault()) }

fun Context.getRealPathFromURI(uri: Uri): String? {
    val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
    cursor?.moveToFirst()
    val idx: Int? = cursor?.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
    return idx?.let { cursor.getString(it) }
}

fun Context.showGrantPermissionAlertDialog(
    permissionName: String,
    positiveButtonText: String,
    onPositiveButtonClick: () -> Unit
) {
    AlertDialog.Builder(this)
        .setTitle(getString(R.string.app_permission))
        .setCancelable(false)
        .setMessage(
            "For choosing image you must allow this permission.\n\nNow follow the steps below\n\nOpen settings from the below button\nClick on permission\nAllow access for $permissionName"
        )
        .setPositiveButton(positiveButtonText) { _, _ ->
            onPositiveButtonClick.invoke()
        }
        .create()
        .show()
}

fun ImageView.loadImagesWithGlide(path: String?) {
    Glide.with(this)
        .load(path)
        .error(R.drawable.ic_user)
        .placeholder(R.drawable.ic_user)
        .into(this)
}