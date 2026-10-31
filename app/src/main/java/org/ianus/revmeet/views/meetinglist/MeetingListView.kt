package org.ianus.revmeet.views.meetinglist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.ianus.revmeet.main.MainApp

    class MeetingListView : AppCompatActivity() {
        lateinit var app: MainApp

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val layoutManager = LinearLayoutManager(this)
        }
    }
