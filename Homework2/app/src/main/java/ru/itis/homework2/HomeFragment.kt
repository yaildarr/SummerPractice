package ru.itis.homework2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.itis.homework2.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var binding: FragmentHomeBinding? = null
    var text: String? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding?.run{
            val bundle = Bundle()
            button.setOnClickListener {
                text = editTextText.text.toString()
                bundle.putString("ARG_TEXT",text)
                if (text!!.isNotEmpty()) {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_resultFragment,
                        args = bundle
                    )
                } else {
                    Snackbar.make(view,"Для отправки текста требуется заполнить поле",Snackbar.LENGTH_SHORT).show()
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}