package com.dsw.timing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dsw.timing.R
import com.dsw.timing.adapter.PandaAdapter
import com.dsw.timing.bean.Panda

class HomeFragment : Fragment() {

    private lateinit var myRecyclerView : RecyclerView
    private lateinit var img_big : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.homefragment_layout, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myRecyclerView = view.findViewById(R.id.recycler_main)
        img_big = view.findViewById(R.id.img_bigsize)

        var items : ArrayList<Panda> = ArrayList<Panda>().also {
            initList(it)
            initList(it)
        }

        myRecyclerView.adapter = PandaAdapter(items, myRecyclerView, img_big)

        myRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }




    private fun initList(lists : ArrayList<Panda>) {
        lists.add(Panda("测试"))
        lists.add(Panda( R.drawable.xihu1, "西湖1"))
        lists.add(Panda( R.drawable.wenjun2, "wenjun1"))

        lists.add(Panda( R.drawable.xihu2, "西湖2"))
        lists.add(Panda( R.drawable.xihu4, "西湖3"))
        lists.add(Panda( R.drawable.wenjun3, "wenjun1"))

        lists.add(Panda( R.drawable.xihu5, "西湖5"))
        lists.add(Panda( R.drawable.xihu7, "西湖3"))
        lists.add(Panda( R.drawable.wenjun4, "wenjun1"))

        lists.add(Panda( R.drawable.xihu8, "西湖3"))
        lists.add(Panda( R.drawable.xihu9, "西湖3"))
        lists.add(Panda( R.drawable.wenjun5, "wenjun1"))

        lists.add(Panda( R.drawable.xihu10, "西湖3"))
        lists.add(Panda( R.drawable.xihu11, "西湖3"))
        lists.add(Panda( R.drawable.wenjun1, "wenjun1"))


    }
}