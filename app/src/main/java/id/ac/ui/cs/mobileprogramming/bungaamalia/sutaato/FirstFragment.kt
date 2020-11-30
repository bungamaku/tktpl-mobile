package id.ac.ui.cs.mobileprogramming.bungaamalia.sutaato

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var viewModel: CountdownViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity()).get(CountdownViewModel::class.java)
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val wifiManager = activity?.applicationContext?.getSystemService(
            Context.WIFI_SERVICE) as WifiManager

        val wifiScanReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val wifiNames = wifiManager.scanResults.map {
                    it.SSID
                }

                if (wifiNames.isNotEmpty()) {
                    view.findViewById<TextView>(R.id.wifi_textView).text =
                        wifiNames.joinToString(
                            prefix = "Wi-Fi names: [", postfix = "]", separator = ", ")
                }
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        activity?.applicationContext?.registerReceiver(wifiScanReceiver, intentFilter)

        view.findViewById<Button>(R.id.wifi_button).setOnClickListener {
            val success = wifiManager.startScan()
            if (success) {
                Toast.makeText(activity?.applicationContext,
                    "Scanning for Wi-Fi, loading...", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(activity?.applicationContext,
                    "Wi-Fi scanning failed, please try again.", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<Button>(R.id.start_button).setOnClickListener {
            val showValueTextView = view.findViewById<TextView>(R.id.textView_input)
            viewModel.currentValue = showValueTextView.text.toString().toInt()
            if (viewModel.currentValue == 0) {
                Toast.makeText(activity?.applicationContext,
                    "Countdown can't start from 0!", Toast.LENGTH_SHORT).show()
            } else {
                val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment()
                findNavController().navigate(action)
            }
        }

        view.findViewById<Button>(R.id.plus_button).setOnClickListener {
            plusValue(view)
        }

        view.findViewById<Button>(R.id.minus_button).setOnClickListener {
            minusValue(view)
        }
    }

    private fun plusValue(view: View) {
        val showValueTextView = view.findViewById<TextView>(R.id.textView_input)
        showValueTextView.text = viewModel.plusValue()
    }

    private fun minusValue(view: View) {
        val showValueTextView = view.findViewById<TextView>(R.id.textView_input)
        var value = viewModel.currentValue
        if (value < 1) {
            Toast.makeText(activity?.applicationContext, "Seconds can't be negative!", Toast.LENGTH_SHORT).show()
        } else {
            showValueTextView.text = viewModel.minusValue()
        }
    }
}