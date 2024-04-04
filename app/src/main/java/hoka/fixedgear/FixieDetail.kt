package hoka.fixedgear

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FixieDetail : AppCompatActivity() {
    private val SHARE_REQUEST_CODE = 123 // kode permintaan untuk berbagi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixie_detail)

        val fixie = intent.getParcelableExtra<Fixie>(MainActivity.INTENT_PARCELABLE)

        val photo = findViewById<ImageView>(R.id.img_item_photo)
        val name = findViewById<TextView>(R.id.tv_item_name)
        val description = findViewById<TextView>(R.id.tv_item_description)

        photo.setImageResource(fixie?.photo!!)
        name.text = fixie.name
        description.text = fixie.description

        val profileButton = findViewById<ImageButton>(R.id.BackButton)
        profileButton.setOnClickListener {
            openMainActivity()
        }

        // Mengatur onClickListener untuk tombol reply
        findViewById<ImageButton>(R.id.ShareButton).setOnClickListener {
            // Ambil isi TextView untuk diposting
            val name = findViewById<TextView>(R.id.tv_item_name).text.toString()
            val description = findViewById<TextView>(R.id.tv_item_description).text.toString()

            // Buat pesan yang akan dibagikan
            val message = "$name\n$description"

            // Buat Intent untuk berbagi pesan
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, message)

            // Mulai aktivitas untuk berbagi pesan dengan requestCode
            startActivityForResult(
                Intent.createChooser(shareIntent, "Share via"), SHARE_REQUEST_CODE
            )
        }
    }

    // Menangani hasil dari aktivitas berbagi
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SHARE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Tampilkan notifikasi bahwa berbagi berhasil
                Toast.makeText(this, "Shared successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
