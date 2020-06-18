package by.agd.rsandroidtask4.view.carlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.agd.rsandroidtask4.databinding.FragmentCarListBinding

class CarListFragment : Fragment() {

    private val viewModel: CarListViewModel by viewModels()

    private lateinit var binding: FragmentCarListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarListBinding.inflate(inflater, container, false)
        return binding.root
    }
}