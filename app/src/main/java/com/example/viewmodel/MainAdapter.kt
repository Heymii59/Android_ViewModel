package com.example.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.viewmodel.databinding.ItemVaccineResultBinding

class MainAdapter(var dataSet: LiveData<ArrayList<VaccineBody>>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVaccineResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = dataSet.value!!.size

    inner class ViewHolder(private val binding: ItemVaccineResultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            dataSet.value!!.get(position).let { item ->
                binding.itemFacilityNameTv.text = item.facilityName
                binding.itemZipCodeTv.text = "(${item.zipCode})"
                binding.itemAddressTv.text = " ${item.address}"
                if(item.phoneNumber.isNullOrBlank()){
                    binding.itemPhoneNumberTv.text = "전화번호를 확인할 수 없습니다."
                } else{
                    binding.itemPhoneNumberTv.text = "${item.phoneNumber}"
                }
            }
        }
    }

}