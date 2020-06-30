package by.agd.rsandroidtask4.view.addcar

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.agd.rsandroidtask4.R
import by.agd.rsandroidtask4.databinding.FragmentAddCarBinding
import by.agd.rsandroidtask4.model.Car
import by.agd.rsandroidtask4.repository.CarOpenHelperRepository
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

private const val PICK_IMAGE_REQUEST_CODE = 123

class AddCarFragment : Fragment() {

    private var _binding: FragmentAddCarBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddCarViewModel by viewModels {
        AddCarViewModelFactory(CarOpenHelperRepository(requireActivity()))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel

        binding.apply {
            selectImageClickListener = View.OnClickListener { openImageProvider() }
            addCarClickListener = OnAddCarClickListener { if (checkHasError(it) == 0) addCar() }
        }
    }

    private fun addCar() {
        runBlocking {
            val itemId = viewModel.addCar()
            itemId?.let {
                val activityView = requireActivity().findViewById<View>(R.id.activityLayout)
                val message = getString(R.string.snackbar_car_added)
                Snackbar.make(activityView, message, Snackbar.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }

    private fun checkHasError(car: Car): Int {
        var errors = 0
        with(binding.carDetailsLayout) {
            errors += hasError(carMakerInputLayout, R.string.input_car_car_maker_error) { car.carMaker < 0 }
            errors += hasError(yearInputLayout, R.string.input_car_year_error) { car.year < 1960 }
            errors += hasError(priceInputLayout, R.string.input_car_price_error) { car.price < 0 }
            errors += hasError(mileageInputLayout, R.string.input_mileage_error) { car.mileage < 0 }
            errors += hasError(bodyInputLayout, R.string.input_card_body_error) { car.body < 0 }
            errors += hasError(colorInputLayout, R.string.input_car_color_error) { car.color < 0 }
            errors += hasError(transmissionInputLayout, R.string.input_car_transmission_error) { car.transmission < 0 }
            errors += hasError(drivetrainInputLayout, R.string.input_car_drivetrain_error) { car.drivetrain < 0 }
        }
        return errors
    }

    private fun openImageProvider() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        }
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
    }

    private fun hasError(layout: TextInputLayout, @StringRes error: Int, condition: () -> Boolean): Int {
        val hasError = condition()
        layout.error = if (hasError) requireContext().getString(error) else null
        return if (hasError) 1 else 0
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != PICK_IMAGE_REQUEST_CODE || resultCode != RESULT_OK) return
        data?.data?.let { viewModel.setImage(it) }
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }
}