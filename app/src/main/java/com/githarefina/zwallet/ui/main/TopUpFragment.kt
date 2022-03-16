package com.githarefina.zwallet.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.githarefina.zwallet.ui.adapter.TopUpAdapter
import com.githarefina.zwallet.databinding.FragmentTopUpBinding
import com.githarefina.zwallet.utils.TopUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopUpFragment : Fragment() {

    private lateinit var binding:FragmentTopUpBinding
    private lateinit var topUpAdapter: TopUpAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopUpBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Topup()
    }
    fun Topup(){
        binding.recyclerTopup.apply {
            layoutManager= LinearLayoutManager(context)
            var list = TopUp.getData()
            adapter = TopUpAdapter(context,list)


        }
    }
}