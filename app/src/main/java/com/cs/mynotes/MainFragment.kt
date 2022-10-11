package com.cs.mynotes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.cs.mynotes.api.NotesAPI
import com.cs.mynotes.databinding.FragmentMainBinding
import com.cs.mynotes.utils.Constants.TAG
import com.cs.mynotes.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel by viewModels<NoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
    }

    private fun bindObservers() {
        noteViewModel.notesLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when(it){
                is NetworkResult.Success -> {}
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),it.message.toString(),Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}