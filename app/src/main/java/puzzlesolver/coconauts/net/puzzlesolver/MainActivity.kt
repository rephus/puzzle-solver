package puzzlesolver.coconauts.net.puzzlesolver

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.net.Uri.*
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.annotation.ColorInt
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.os.Environment.getExternalStorageDirectory
import android.support.v4.content.ContextCompat
import java.io.File
import android.support.v4.content.FileProvider
import android.graphics.BitmapFactory
import android.hardware.Camera


class MainActivity : AppCompatActivity() {

    val CAMERA_REQUEST_CODE=0
    val CAMERA_REQUEST_PIECE_CODE=1

    var puzzleBitmap : Bitmap? = null
    var pieceBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE);
        ContextCompat.checkSelfPermission(this,
            Manifest.permission.CAMERA);
/*
        //if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
        val root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val storageDir = File(
            "/storage/emulated/0/Donwloads/" /*Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM
            )*/ + "Camera.jpg"
        )
        println("DCIM " + storageDir)
        val image = File.createTempFile(
            "temp", /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )
        val photoURI = FileProvider.getUriForFile(
            applicationContext,
            applicationContext.getApplicationContext().getPackageName() + ".net.coconauts.puzzlesolver.provider",
            image
        )
*/
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            "puzzlesolver", /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )
        val     imageFilePath = image.getAbsolutePath();

        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        //val dir = File(applicationContext.getFilesDir(), "Pictures")
        // me aseguro de que el directorio exista y de no ser así lo creo
            dir.mkdirs()
        val file = File(dir, "puzzleResolver.jpg")

        println("PATATA path " + file.getPath() )
        val photoURI = FileProvider.getUriForFile(this, "puzzlesolver.coconauts.net.puzzlesolver", file)
        println("PATATA " + photoURI)
        // val filename = Environment.getExternalStorageDirectory().getPath() + "/Download/camera.jpg"
        //val imageFile = File(filename)
        //val imageUri = fromFile(imageFile)

        //}


        /*
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()


        }*/


        fab.setOnClickListener{
            /*val mCamera = Camera.open();
            val params = mCamera.getParameters();

// Check what resolutions are supported by your camera
            val sizes = params.getSupportedPictureSizes();

// Iterate through all available resolutions and choose one.
// The chosen resolution will be stored in mSize.
            for ( size in  sizes) {
                println( "Available resolution: "+size.width+" "+size.height);
            }


            //params.setPictureSize(mSize.width, mSize.height);
            //mCamera.setParameters(params);
            */

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
        imageView.setOnClickListener {
            println("Click on image");

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CAMERA_REQUEST_CODE ->{

                if(resultCode== Activity.RESULT_OK && data != null){

                    puzzleBitmap = data.extras.get("data") as Bitmap

                    //puzzleBitmap = b.copy( Bitmap.Config.ARGB_8888 , true);
                    val bitmap = puzzleBitmap!!

                    imageView.setImageBitmap(bitmap)

                        /*
                        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                        dir.mkdirs()
                        val file = File(dir, "puzzleResolver.jpg")
                        val photoURI = FileProvider.getUriForFile(this, "puzzlesolver.coconauts.net.puzzlesolver", file)
                        imageView.setImageURI(photoURI)

                        println("PATATA Saving file on " + file.getAbsolutePath())

                        val bitmap = BitmapFactory.decodeFile(file.getAbsolutePath())

                    */

                     /*
                    val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    dir.mkdirs()
                    val file = File(dir, "puzzleResolver.jpg")
                    val photoURI = FileProvider.getUriForFile(this, "puzzlesolver.coconauts.net.puzzlesolver", file)
                    imageView.setImageURI(photoURI)

                    println("PATATA Saving file on " + file.getAbsolutePath())

                    val bitmap = BitmapFactory.decodeFile(file.getAbsolutePath())
                    */

                    /*
                    println("PATATA SIZE " + bitmap.width  + " x" + bitmap.height)
                    val randomW = (0..bitmap.width).shuffled().first()
                    val randomH = (0..bitmap.height).shuffled().first()
                    println("PATATA random xy " + randomW + ", "+ randomH)
                    val pixel = bitmap.getPixel(randomW, randomH)
                    */

                    //Thread().run  {
                      /*  println("PATATA pixel search")
                        for (x in 0..bitmap.width-1) {
                            //println("PATATA x " + x)
                            for (y in 0..bitmap.height-1) {
                                val p = bitmap.getPixel(x, y)
                                if (p == pixel)  {

                                    println("PATATA found at " + x + ", "+ y)
                                    bitmap.setPixel(x,y, Color.RED);
                                }


                            }
                        }*/

                    //}


                    /*for (x in 0..bitmap.width-1) {
                        for (y in 0..bitmap.height-1) {
                            val p = bitmap.getPixel(x, y)
                            if (p == pixel)  {

                                println("PATATA found at " + x + ", "+ y)
                                bitmap.setPixel(x,y, Color.RED);
                            }


                        }
                    }*/
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
                            println("PATATA Pixel " + pixel)

                            bColor.setPixel(delta, 0 , pixel)

                            println("Looking for pixel on puzzle bitmap " + pBitmap)
                            for (x in 0..pBitmap.width-1) {
                                //println("PATATA x " + x)
                                for (y in 0..pBitmap.height-1) {
                                    val p = pBitmap.getPixel(x, y)
                                    if (p == pixel)  {

                                        println("PATATA found at " + x + ", "+ y)
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
