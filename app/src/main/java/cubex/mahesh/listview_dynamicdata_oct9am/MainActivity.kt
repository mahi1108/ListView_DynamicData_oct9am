package cubex.mahesh.listview_dynamicdata_oct9am

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var status = ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE)
        if(status == PackageManager.PERMISSION_GRANTED)
        {
            readFiles()
        }else{
            ActivityCompat.requestPermissions(this@MainActivity,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    123)
        }
    } // onCreate( )
    fun  readFiles( )
    {
        var path = "/storage/sdcard0/"
        var f = File(path)
        if(!f.exists()){
            path = "/storage/emulated/0/"
            f= File(path)
        }
        var files:Array<String> = f.list()
        var adapter = ArrayAdapter<String>(this@MainActivity,
                android.R.layout.simple_list_item_single_choice,files)
        lview.adapter = adapter

    } // readFiles( )

    override fun onRequestPermissionsResult(requestCode: Int,
                permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode,
                permissions, grantResults)

        if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {    // invoked when we click allow button
            readFiles()
        }else{   // invoked when we click deny button
            Toast.makeText(this@MainActivity,
                    "Please provide a permission",
                    Toast.LENGTH_LONG).show()
        }
    }



} // MainActivity
