package by.agd.rsandroidtask4.view.carlist

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import by.agd.rsandroidtask4.R
import by.agd.rsandroidtask4.adapter.car.CarKeyProvider
import by.agd.rsandroidtask4.adapter.car.CarListAdapter
import by.agd.rsandroidtask4.adapter.car.CarLookup
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
            val tracker = SelectionTracker
                .Builder<Car>(
                    // идентифицируем трекер в контексте
                    "someId",
                    binding.carList,
                    // для Long ItemKeyProvider реализован в виде StableIdKeyProvider
                    CarKeyProvider(it),
                    CarLookup(binding.carList),
                    // существуют аналогичные реализации для Long и String
                    StorageStrategy.createParcelableStorage(Car::class.java)
                ).build()

            tracker.addObserver(object : SelectionTracker.SelectionObserver<Any>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    with(requireActivity()) {
                        if (tracker.hasSelection() && actionMode == null) {
                            actionMode = startActionMode(ActionModeController(tracker))
                            setSelectedTitle(tracker.selection.size())
                        } else if (!tracker.hasSelection()) {
                            actionMode?.finish()
                            actionMode = null
                        } else {
                            setSelectedTitle(tracker.selection.size())
                        }
                    }
                }
            })
            carAdapter.setItems(it)
            carAdapter.setTracker(tracker)
        })
    }

    private var actionMode: ActionMode? = null

    private fun setSelectedTitle(selected: Int) {
        actionMode?.title = "Selected: $selected"
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

class ActionModeController(
    private val tracker: SelectionTracker<*>
) : ActionMode.Callback {

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        //  mode.menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode) {
         Log.d("Hello",tracker.selection.joinToString(", "))
        tracker.clearSelection()
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean = true

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean = false
    //when (item.itemId) {
//        R.id.action_clear -> {
//            mode.finish()
//            true
//        }
//        else ->
//        false
}
