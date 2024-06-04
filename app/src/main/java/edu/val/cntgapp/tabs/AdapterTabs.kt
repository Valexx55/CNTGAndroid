package edu.`val`.cntgapp.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterTabs(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {

    val array_datos = intArrayOf(1, 2, 3)
    override fun getItemCount(): Int {
       return array_datos.size
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment

            fragment = FragmentoTabs()
            val bundle = Bundle()
            bundle.putInt("VALOR", array_datos[position])
            fragment.arguments = bundle

        return fragment

    }


}