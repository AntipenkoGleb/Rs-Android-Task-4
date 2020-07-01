package by.agd.rsandroidtask4.view.carlist

import android.os.Bundle
import android.view.*
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import by.agd.rsandroidtask4.R
import by.agd.rsandroidtask4.adapter.car.CarListAdapter
import by.agd.rsandroidtask4.adapter.car.SelectionTrackerController
import by.agd.rsandroidtask4.databinding.FragmentCarListBinding
import by.agd.rsandroidtask4.model.Car
import by.agd.rsandroidtask4.repository.CarOpenHelperRepository

class CarListFragment : Fragment() {

    private val viewModel: CarListViewModel by viewModels {
        CarListViewModelFactory(CarOpenHelperRepository(requireActivity()))
    }

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

        val carAdapter =
            CarListAdapter(emptyList())

        binding.apply {
            carList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@CarListFragment.context)
                adapter = carAdapter
            }
            fab.setOnClickListener { navigateTo(R.id.action_carListFragment_to_addCarFragment) }
        }

        viewModel.onViewCreated()
        viewModel.cars.observe(viewLifecycleOwner, Observer {
            val tracker = createCarSelectionTracker(it)
            carAdapter.setItems(it)
            carAdapter.setTracker(tracker)
        })
    }

    private fun createCarSelectionTracker(items: List<Car>): SelectionTracker<Car> {
        val activity = requireActivity() as AppCompatActivity
        val storageStrategy = StorageStrategy.createParcelableStorage(Car::class.java)
        val trackerController =
            SelectionTrackerController<Car>(activity, binding.carList, items, storageStrategy)
        return trackerController.tracker
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
