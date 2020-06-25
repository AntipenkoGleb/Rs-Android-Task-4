package by.agd.rsandroidtask4.view.addcar

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.agd.rsandroidtask4.databinding.FragmentAddCarBinding
import by.agd.rsandroidtask4.repository.CarOpenHelperRepository


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
            selectImageClickListener = View.OnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    type = "image/*"
                    putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
                }
                startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE)
            }

            addCarClickListener = OnAddCarClickListener { car ->
                var hasError = false
                with(carDetailsLayout) {

                    if (car.carMaker < 0) {
                        hasError = true
                        carMakerInputLayout.isErrorEnabled = true
                        carMakerInputLayout.error = "Car maker required"
                    } else carMakerInputLayout.isErrorEnabled = false



                    if (car.body < 0) {
                        hasError = true
                        bodyInputLayout.error = "Car maker required"
                    }
                }
                if (!hasError)
                    viewModel!!.addCar()
            }
        }
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