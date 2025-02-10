package ms.example.hw_8_9

import android.icu.util.Calendar
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import ms.example.hw_8_9.databinding.FragmentFirstBlankBinding
import java.text.SimpleDateFormat

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstBlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstBlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentFirstBlankBinding? = null
    private val binding get() = _binding!!

    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("dd.MM.yyyy hh:mm")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBlankBinding.inflate(layoutInflater)

        binding.buttonfirst.setOnClickListener {

            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.choose_the_date))
                .setSelection(calendar.timeInMillis)
                .build()

                dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
                    calendar.timeInMillis = timeInMillis
//                    val day = calendar.get(Calendar.DAY_OF_MONTH)
//                    val month = calendar.get(Calendar.MONTH) + 1
//                    val year = calendar.get(Calendar.YEAR)
//
//                    val text = "$day / $month / $year"

                    Snackbar.make(binding.buttonfirst, dateFormat.format(calendar.time), Snackbar.LENGTH_LONG).show()

                }
                dateDialog.show(parentFragmentManager,"DatePicker")

        binding.buttonsecond.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setTitleText(resources.getString(R.string.choose_the_time))
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build().apply {
                    addOnPositiveButtonClickListener {
                        calendar.set(Calendar.HOUR, this.hour)
                        calendar.set(Calendar.MINUTE, this.minute)
                        Snackbar.make(binding.buttonsecond, dateFormat.format(calendar.time), Snackbar.LENGTH_LONG).show()
                    }
                }
            picker.show(parentFragmentManager, "TimePicker")

        }

            val bundle = Bundle().apply {
                putString("param1",binding.vvodtext.text.toString())
            }

            /*parentFragmentManager.commit {
                replace<SecondBlankFragment>(containerViewId = R.id.fragmentinactivity, args = bundle)
                addToBackStack(SecondBlankFragment::class.java.simpleName)
            }*/
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstBlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstBlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}