package edu.`val`.cntgapp.tabs

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import edu.`val`.cntgapp.R

class TabsActivity : AppCompatActivity(), TabLayoutMediator.TabConfigurationStrategy {

    lateinit var viewPager2: ViewPager2
    lateinit var tabLayout: TabLayout
    lateinit var adapterTabs: AdapterTabs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        this.viewPager2 = findViewById<ViewPager2>(R.id.vpt)
        this.tabLayout = findViewById<TabLayout>(R.id.tablayout)
        this.adapterTabs = AdapterTabs(this)

        this.viewPager2.adapter = this.adapterTabs
        //asociar al tablayout el viewpager
        TabLayoutMediator(tabLayout, viewPager2, this).attach()


    }

    //cada vez que está activo el tab, se invoca a este método para poder modificar su apariencia
    override fun onConfigureTab(tab: TabLayout.Tab, pos: Int) {
       tab.text = "VISTA ${pos+1}"
    }
}