package hoka.fixedgear

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object{
    val INTENT_PARCELABLE = "OBJECT_INTENT"
    }
    private lateinit var rvFixie: RecyclerView
    private val list = ArrayList<Fixie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvFixie = findViewById(R.id.rv_fixie)
        rvFixie.setHasFixedSize(true)

        list.addAll(getListFixie())
        showRecyclerList()


        val profileButton = findViewById<ImageButton>(R.id.ProfileButton)
        profileButton.setOnClickListener {
            openProfileActivity()
        }
    }

    private fun getListFixie(): ArrayList<Fixie> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listFixie = ArrayList<Fixie>()
        for (i in dataName.indices) {
            val fixie = Fixie(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listFixie.add(fixie)
        }
        return listFixie
    }

    private fun showRecyclerList() {
        rvFixie.layoutManager = LinearLayoutManager(this)
        val listFixieAdapter = ListFixieAdapter(list) { fixie ->
            val intent = Intent(this@MainActivity, FixieDetail::class.java)
            intent.putExtra(INTENT_PARCELABLE, fixie)
            startActivity(intent)
        }
        rvFixie.adapter = listFixieAdapter
    }

    private fun openProfileActivity() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
}