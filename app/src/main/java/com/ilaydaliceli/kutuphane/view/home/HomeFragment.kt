package com.ilaydaliceli.kutuphane.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilaydaliceli.kutuphane.R
import com.ilaydaliceli.kutuphane.adapter.BookAdapter
import com.ilaydaliceli.kutuphane.databinding.ActivityMainBinding
import com.ilaydaliceli.kutuphane.databinding.FragmentHomeBinding
import com.ilaydaliceli.kutuphane.util.ApplicationViewModelFactory
import com.ilaydaliceli.kutuphane.viewmodel.MainViewModel

class HomeFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels {
        ApplicationViewModelFactory(requireActivity().application)
    }
    private lateinit var binding: FragmentHomeBinding
    private var bookAdapter = BookAdapter(arrayListOf()){position ->
        findNavController().navigate(R.id.action_homeFragment3_to_detailFragment2)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.bookRV.adapter = bookAdapter
        binding.bookRV.layoutManager= LinearLayoutManager(requireContext())
        viewModel.getDataFromAPI()
        setObservers()
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setObservers(){
        viewModel.bookData.observe(viewLifecycleOwner, Observer {list->
            bookAdapter.updateList(list)
            viewModel.insertAll(list)
        })
        viewModel.bookLoad.observe(viewLifecycleOwner, Observer{loading ->
            if(loading){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        })
        viewModel.bookError.observe(viewLifecycleOwner,Observer{error->
            if(error){
                binding.errorTV.visibility = View.VISIBLE
            }else{
                binding.errorTV.visibility = View.GONE
            }
        })

    }

}