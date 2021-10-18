package com.example.macaronagaintoay.Fragment

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.macaronagaintoay.Adapter.SearchListAdapter
import com.example.macaronagaintoay.Dao.OnItemClick
import com.example.macaronagaintoay.Decorator.HorizontalItemDecorator
import com.example.macaronagaintoay.Decorator.VerticallItemDecorator
import com.example.macaronagaintoay.entity.SearchEntity
import com.example.macaronagaintoay.R
import com.example.macaronagaintoay.databinding.FragmentSearchBinding
import com.example.macaronagaintoay.viewmodel.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchFragment : Fragment(), OnItemClick {

    private val viewModel : SearchViewModel by viewModels()

    lateinit var binding : FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)

        binding.search = viewModel



        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL; // horizonta, vertical 옵션에 따라 가로/세로 list

        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.layoutManager = layoutManager

        val mAdapter = SearchListAdapter(requireActivity(),this)

        binding.recyclerview.adapter = mAdapter

        binding.recyclerview.addItemDecoration(HorizontalItemDecorator(10))
        binding.recyclerview.addItemDecoration(VerticallItemDecorator(5))


        viewModel.allSearch.observe(viewLifecycleOwner, Observer { search ->
            search.let {
                mAdapter.setSearch(it)
                mAdapter.notifyDataSetChanged()
            }
        })


        binding.searchEt.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            //Enter key Action
            if (event.action === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {

                viewModel.insert(SearchEntity(null, binding.searchEt.text.toString()))

                binding.searchEt.text.isEmpty()

                //Enter키눌렀을떄 처리
                true
            } else false
        })





        return binding.root

    }

    override fun deleteItem(searchEntity: SearchEntity) {
        lifecycleScope.launch(Dispatchers.IO){
            viewModel.deletes(searchEntity)
        }
    }
}