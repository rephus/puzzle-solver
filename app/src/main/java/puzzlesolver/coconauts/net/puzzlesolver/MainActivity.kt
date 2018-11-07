package puzzlesolver.coconauts.net.puzzlesolver

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.support.v4.content.ContextCompat


class MainActivity : AppCompatActivity() {

    val CAMERA_REQUEST_CODE=0
    val CAMERA_REQUEST_PIECE_CODE=1

    var puzzleBitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE);
        ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA);

        // Save full resolution image on external file (instead of thumbnail)
        /*
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        dir.mkdirs()
        val file = File(dir, "puzzleResolver.jpg")
        val photoURI = FileProvider.getUriForFile(this, "puzzlesolver.coconauts.net.puzzlesolver", file)
        */

        fab.setOnClickListener{

            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //callCameraIntent.putExtra( android.provider.MediaStore.EXTRA_OUTPUT,     photoURI)

            if(callCameraIntent.resolveActivity(packageManager)!=null){
                startActivityForResult(callCameraIntent,
                   CAMERA_REQUEST_CODE)
            }
        }

        fab2.setOnClickListener{

            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(callCameraIntent.resolveActivity(packageManager)!=null){
                startActivityForResult(callCameraIntent,
                     CAMERA_REQUEST_PIECE_CODE)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CAMERA_REQUEST_CODE ->{

                if(resultCode== Activity.RESULT_OK && data != null){

                    puzzleBitmap = data.extras.get("data") as Bitmap
                    imageView.setImageBitmap(puzzleBitmap)
                }
            }

            CAMERA_REQUEST_PIECE_CODE -> {


                if(resultCode== Activity.RESULT_OK && data !=null && puzzleBitmap != null){
                        val piezeBitmap = data.extras.get("data") as Bitmap
                        imageView2.setImageBitmap(piezeBitmap)

                        val pBitmap =  puzzleBitmap!!.copy( Bitmap.Config.ARGB_8888 , true);

                        val bColor = Bitmap.createBitmap(7, 1, Bitmap.Config.ARGB_8888)
                        /*for (x in 0..31){
                            for (y in 0..31){
                                bColor.setPixel(x, y , pixel)
                            }
                        }*/
                        var found = 0
                        for ( delta in 0..6)  {

                            val pixel = piezeBitmap.getPixel((delta - 3)  + piezeBitmap.width / 2 , piezeBitmap.height / 2)

                            bColor.setPixel(delta, 0 , pixel)

                            for (x in 0..pBitmap.width-1) {
                                for (y in 0..pBitmap.height-1) {
                                    val p = pBitmap.getPixel(x, y)
                                    if (p == pixel)  {

                                        pBitmap.setPixel(x,y, Color.RED);
                                        found++
                                    }
                                }
                            }
                            imageView.setImageBitmap(pBitmap)
                        }
                        textView.text = "Found " + found + " matches"
                        imageView3.setImageBitmap(bColor)

                } else {
                    Toast.makeText(this,"Take picture of puzzle first",Toast.LENGTH_SHORT)
                }
            }
            else -> {
                Toast.makeText(this,"Unrecognized request code",Toast.LENGTH_SHORT)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
