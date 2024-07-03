package ru.itis.summerpractice24

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment

class NumberPickerDialog : DialogFragment() {
    var valueChangeListener: NumberPicker.OnValueChangeListener? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_number_picker, null)

        val numberPicker = view.findViewById<NumberPicker>(R.id.numberPicker)
        numberPicker.minValue = 0
        numberPicker.maxValue = 50

        builder.setView(view)
            .setTitle("Выберите Число")
            .setPositiveButton("OK") { dialog, id ->
                valueChangeListener?.onValueChange(numberPicker, numberPicker.value, numberPicker.value)
            }
            .setNegativeButton("Cancel") { dialog, id ->
                this@NumberPickerDialog.dialog?.cancel()
            }

        return builder.create()
    }
}