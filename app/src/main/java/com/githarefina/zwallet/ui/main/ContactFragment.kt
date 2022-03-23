package com.githarefina.zwallet.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.githarefina.zwallet.R
import com.githarefina.zwallet.ui.adapter.ContactAdapter
import com.githarefina.zwallet.databinding.FragmentContactBinding
import com.githarefina.zwallet.utils.PREFS_NAME
import com.githarefina.zwallet.utils.State
import com.githarefina.zwallet.viewmodel.TransferViewModel
import com.githarefina.zwallet.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
class ContactFragment : Fragment(){
    private lateinit var prefs: SharedPreferences
    private lateinit var searchView: SearchView
    private lateinit var binding: FragmentContactBinding
    private lateinit var adapterContact: ContactAdapter
    private lateinit var loadingDialog: LoadingDialog
    private val viewModel: TransferViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentContactBinding.inflate(inflater,container,false)
        prefs = activity?.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)!!
        loadingDialog = LoadingDialog(requireActivity())
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchData()
        getContact()
    }



    fun searchData() {

        binding.editTextTextPersonName3
            .setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()){

                    getContactName("")
                }else if(!query.isNullOrEmpty()){
                    viewModel.setName(query)
                    getContactName(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()){
                        getContactName("")
                }else if(!newText.isNullOrEmpty()){
                        viewModel.setName(newText)
                        getContactName(newText)
                }

                return true
            }
        })

    }
    fun getContact() {
        this.adapterContact = ContactAdapter(activity, arrayListOf(), viewModel)
        binding.contact.apply {
            adapter = adapterContact
            layoutManager = LinearLayoutManager(context)
        }
        adapterContact.setOnItemClickListener(ContactAdapter.OnClickListener {
            viewModel.receiver(it.id)
            viewModel.setContact(it)
        })
       var name:String?=null
       viewModel.name.observe(viewLifecycleOwner, Observer {
           name=it
        })
        if(name.isNullOrEmpty()){
            viewModel.getContact().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                when (it.state) {
                    State.LOADING -> {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                    State.SUCCESS -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            contact.visibility = View.VISIBLE
                        }
                        if (it.data?.status == HttpsURLConnection.HTTP_OK) {
                            this.adapterContact.apply {
                                addData(it.data.data!!)
                                notifyDataSetChanged()
                            }
                        }
                    }
                    State.ERROR -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                        }


                    }
                }
            })
        }


    }
        fun getContactName(name:String){
            viewModel.getContactName(name!!).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                when (it.state) {
                    State.LOADING -> {
                        binding.apply {
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                    State.SUCCESS -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                            contact.visibility = View.VISIBLE
                        }
                        if (it.data?.status == HttpsURLConnection.HTTP_OK) {
                            this.adapterContact.apply {
                                addData(it.data.data!!)
                                notifyDataSetChanged()
                            }
                        }
                    }
                    State.ERROR -> {
                        binding.apply {
                            progressBar.visibility = View.GONE
                        }


                    }
                }
            })
        }












    }
