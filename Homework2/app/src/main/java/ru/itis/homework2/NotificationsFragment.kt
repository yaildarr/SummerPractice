package ru.itis.homework2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.itis.homework2.databinding.FragmentNotificationsBinding


class NotificationsFragment : Fragment(R.layout.fragment_notifications) {
    private var binding: FragmentNotificationsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationsBinding.bind(view)

        binding?.run{

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}