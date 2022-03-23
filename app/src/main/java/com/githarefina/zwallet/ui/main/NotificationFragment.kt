package com.githarefina.zwallet.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.githarefina.zwallet.data.model.DateModel
import com.githarefina.zwallet.data.model.Invoice
import com.githarefina.zwallet.databinding.FragmentNotificationBinding
import com.githarefina.zwallet.ui.adapter.AdapterNotification
import com.githarefina.zwallet.utils.PREFS_NAME
import com.githarefina.zwallet.utils.State
import com.githarefina.zwallet.viewmodel.NotificationViewModel
import com.githarefina.zwallet.widget.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : Fragment() {
    private lateinit var binding:FragmentNotificationBinding
//    private lateinit var adapterNotification: NotificationAdapter
    private lateinit var adapterNotification: AdapterNotification

    private val viewModel: NotificationViewModel by activityViewModels()
    private lateinit var loadingDialog: LoadingDialog
    private lateinit var prefs: SharedPreferences
    private lateinit var history: MutableList<Invoice>
    private lateinit var notification:MutableList<DateModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater,container,false)
        prefs = activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)!!
        loadingDialog = LoadingDialog(requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()

    }
    fun getData(){
        this.adapterNotification = AdapterNotification(activity, listOf(),viewModel,viewLifecycleOwner)
        binding.recyclerNotification.apply {
            adapter = adapterNotification
            layoutManager = LinearLayoutManager(context)
        }
        viewModel.getAllInvoice().observe(viewLifecycleOwner, Observer {

         when(it.state){
             State.LOADING->{
                 Toast.makeText(activity,it.message,Toast.LENGTH_LONG)
                 binding.apply {
                     progressBar2.visibility = View.VISIBLE
                     recyclerNotification.visibility = View.GONE
                 }
             }
             State.SUCCESS->{
                 binding.apply {
                     progressBar2.visibility = View.GONE
                     recyclerNotification.visibility = View.VISIBLE
                 }
//                 for (i in it.data!!.data!!.indices) {
//
//                     val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
//                     val date = formatter.parse(it.data.data?.get(i)?.createdAt)
//                     val day: Int = DateFormat.format("dd", date)  as Int
//                     val month :Int= DateFormat.format("MM", date) as Int
//                     binding.textCreatePin.text = day.toString()
//                     val calendar: Calendar = Calendar.getInstance()
//                     val month_now: Int = calendar.get(Calendar.MONTH)
//                     val day_now: Int = calendar.get(Calendar.DATE)
//                     if ((day)>=(day_now - 7)){
//                        history.add(it.data.data?.get(i)!!)
//                        var dateModelWeeks = DateModel("This week", it.data.data!!.get(i).createdAt.toString(),history)
//                        notification.add(dateModelWeeks)
//
//                     }
//                     if(day==(day_now)){
//                         history.add(it.data.data?.get(i)!!)
//                         var dateModelToday = DateModel("Today", it.data.data!!.get(i).createdAt.toString(),history)
//                         notification.add(dateModelToday)
//                     }
//                     if(month>=(month_now - 30)){
//                         history.add(it.data.data?.get(i)!!)
//                         var dateModelMonth = DateModel("This Month", it.data.data!!.get(i).createdAt.toString(),history)
//                         notification.add(dateModelMonth)
//                     }
//                 }
                 this.adapterNotification.apply {
                     addData(it.data?.data!!)
                     notifyDataSetChanged()
                 }



             }
             State.ERROR->{
                 loadingDialog.stop()


             }

         }






        })

    }



}