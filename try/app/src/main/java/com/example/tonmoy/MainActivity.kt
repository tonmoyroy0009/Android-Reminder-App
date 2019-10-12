package com.example.tonmoy

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.lay_input.*
import kotlinx.android.synthetic.main.lay_input.view.*
import java.util.*

class MainActivity : AppCompatActivity() {

    //val realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        init4()

    }

    fun init4()
    {
        init2()
        //getData()
    }

    fun init2()
    {
        floatingActionButton.setOnClickListener()
        {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.lay_input, null)
            val mBuilder = android.app.AlertDialog.Builder(this).setView(mDialogView)
            val ad = mBuilder.show()
            //val btn = mDialogView.search_btn

            var save = mDialogView.save
            var cancel = mDialogView.cancel


            var date_btn = mDialogView.date_btn
            var time_btn = mDialogView.time_btn
            var date = mDialogView.date
            var time = mDialogView.time

            var task = mDialogView.task

            cancel.setOnClickListener()
            {
                ad.dismiss()
            }

            date_btn.setOnClickListener()
            {
                val c = Calendar.getInstance()
                var year = c.get(Calendar.YEAR)
                var month = c.get(Calendar.MONTH)
                var day = c.get(Calendar.DATE)

                var dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, myear, mmonth, mdayOfMonth ->
                    var t  = myear.toString()+"-"+mmonth.toString()+"-"+mdayOfMonth.toString()
                    date.text = t

                    //Toast.makeText(this, t, Toast.LENGTH_LONG).show()
                },year, month, day)

                //date
                dpd.show()
            }

            time_btn.setOnClickListener()
            {
                val t = Calendar.getInstance()

                var hour = t.get(Calendar.HOUR)
                var minute = t.get(Calendar.MINUTE)
                var am_pm = t.get(Calendar.AM_PM)

//                var tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener( function = {view, hourOfDay, mminute ->
//                    var d = hourOfDay.toString()+":"+mminute.toString()
//
//                    time.text = d
//                    Toast.makeText(this, d, Toast.LENGTH_LONG).show()
//
//                }), hour, minute, false)

                var tpd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, mminute ->

                    var d = hourOfDay.toString()+":"+mminute.toString()
                    time.text = d
                    //Toast.makeText(this, d, Toast.LENGTH_LONG).show()

                }, hour, minute, false)


                tpd.show()
            }

            save.setOnClickListener()
            {
                var task = mDialogView.task.toString()
                var date_data = date.text.toString()
                var time_data = time.text.toString()
                var task_data = mDialogView.task.text.toString()

                if (date_data.isNotBlank() && time_data.isNotBlank() && task_data.isNotBlank())
                {
                    Toast.makeText(this, date_data+" "+time_data+" "+task_data, Toast.LENGTH_LONG).show()

                    val realm = Realm.getDefaultInstance()
                    realm.beginTransaction()
                    var data:Data = Data(task_data,date_data,time_data)
                    realm.copyToRealm(data)
                    realm.commitTransaction()

                    Toast.makeText(this, "saved", Toast.LENGTH_LONG).show()
                    init2()
                }
                else
                {
                    Toast.makeText(this, "Please check your input", Toast.LENGTH_LONG).show()
                }
            }
        }



        val realm = Realm.getDefaultInstance()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        realm.beginTransaction()
        var data = realm.where(Data::class.java).findAll()
        realm.commitTransaction()
        var adapter = AdapterData(data)
        recyclerView.adapter = adapter
    }

    fun getData()
    {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        var data = realm.where(Data::class.java).findAll()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)

        //val recyclerView = findViewById<RecyclerView>(R.id.recycler)
            //recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        val adapter = AdapterData(data)
        recyclerView.adapter = adapter
    }
}
