package ru.itis.homework2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.itis.homework2.databinding.FragmentResultBinding


class ResultFragment : Fragment(R.layout.fragment_result) {

    private var binding: FragmentResultBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultBinding.bind(view)

        val text = arguments?.getString("ARG_TEXT")

        binding?.run{
            resultText.text = text
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}