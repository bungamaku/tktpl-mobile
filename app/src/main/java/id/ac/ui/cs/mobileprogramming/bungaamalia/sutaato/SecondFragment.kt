package id.ac.ui.cs.mobileprogramming.bungaamalia.sutaato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    private val args: SecondFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        val value = args.randomArg
        val valueText = getString(R.string.random_heading, value)
        view.findViewById<TextView>(R.id.textView_header).text = valueText

        val random = java.util.Random()
        var randomNumber = 0
        if (value > 0) {
            randomNumber = random.nextInt(value + 1)
        }
        view.findViewById<TextView>(R.id.textView_random).text = randomNumber.toString()
    }
}