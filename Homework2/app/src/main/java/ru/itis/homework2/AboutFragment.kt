package ru.itis.homework2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.itis.homework2.databinding.FragmentAboutBinding
import ru.itis.homework2.databinding.FragmentHomeBinding


class AboutFragment : Fragment(R.layout.fragment_about) {
    private var binding: FragmentAboutBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutBinding.bind(view)

        binding?.run{

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}