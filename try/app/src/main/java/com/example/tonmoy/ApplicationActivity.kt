package com.example.tonmoy

import android.app.Application
import io.realm.Realm

class ApplicationActivity : Application()
{

    override fun onCreate()
    {
        super.onCreate()
        Realm.init(this)
    }
}

