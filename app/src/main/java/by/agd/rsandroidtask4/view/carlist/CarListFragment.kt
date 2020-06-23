package by.agd.rsandroidtask4.view.carlist

import android.os.Bundle
import android.view.*
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.agd.rsandroidtask4.R
import by.agd.rsandroidtask4.adapter.CarListAdapter
import by.agd.rsandroidtask4.databinding.FragmentCarListBinding
import by.agd.rsandroidtask4.model.Car

class CarListFragment : Fragment() {

    private val viewModel: CarListViewModel by viewModels()

    private var _binding: FragmentCarListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarListBinding.inflate(inflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carAdapter = CarListAdapter(
            listOf(
                Car(0, 1, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 2, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 3, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 4, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 5, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 0, "Actava", 15690f, null, 0, 0, 2006, 2354215, 0, 0, "Lorum ipsum"),
                Car(0, 15, "Senia", 15690f, null, 2, 2, 2006, 2354215, 4, 1, "Lorum ipsum")
            )
        )

        binding.carList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CarListFragment.context)
            adapter = carAdapter
        }

        binding.fab.setOnClickListener { navigateTo(R.id.action_carListFragment_to_addCarFragment) }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_filter, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.filter) {
            navigateTo(R.id.action_carListFragment_to_settingsFragment)
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun navigateTo(@IdRes resId: Int) {
        findNavController().navigate(resId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}