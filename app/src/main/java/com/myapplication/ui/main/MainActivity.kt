package com.myapplication.ui.main

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.R
import com.myapplication.adapter.MyGroupAdapter
import com.myapplication.utils.AudioItem2
import com.xwray.groupie.Section
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity : AppCompatActivity() {
    val REQUEST_CODE_FOR_ON_ACTIVITY_RESULT=98378
    var audiosList= mutableListOf<Uri>()
    lateinit var adapter: MyGroupAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setupRecyclerView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_FOR_ON_ACTIVITY_RESULT -> if (resultCode === Activity.RESULT_OK) {
            if (null != data) {
                val section = Section()
                if (null !=data.clipData) {
                    for (i in 0 until data?.clipData!!.itemCount) {
                        val uri = data.clipData?.getItemAt(i)?.uri
                        dumpAudioMetaData(uri,section)
                    }
                } else {
                    val uri = data.data
                    dumpAudioMetaData(uri,section)
                }

                adapter.add(section)
                adapter.notifyDataSetChanged()




            }
        }
        }
    }

    private fun dumpAudioMetaData(uri: Uri?, section: Section) {
        section.add(AudioItem2(uri!!))
    }

    private fun setupRecyclerView() {
        adapter = MyGroupAdapter()


        audioRecyclerView.layoutManager= LinearLayoutManager(this)
        audioRecyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings ->{
                val filesIntent: Intent
                filesIntent = Intent(Intent.ACTION_GET_CONTENT)
                filesIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                filesIntent.addCategory(Intent.CATEGORY_OPENABLE)
                filesIntent.type = "audio/*"

                startActivityForResult(filesIntent, REQUEST_CODE_FOR_ON_ACTIVITY_RESULT)
            }
        }

        return super.onOptionsItemSelected(item)
    }


}