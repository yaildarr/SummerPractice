package ru.itis.summerpractice24

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.itis.summerpractice24.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var selectedNumberTextView: TextView
    var countCurrentCars = 0
    var countCars: Int = 0
    lateinit var selectedType: String
    lateinit var selectedMark: String
    lateinit var selectedModel: String

    var selectedYear: Int = 0
    var selectedPower: Int = 0
    var selectedTimeTo100: Double = 0.0

    private var autoCollection = mutableListOf<Auto>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val constraintMain: ConstraintLayout = findViewById(R.id.constraintLayoutpage1)
        val constraintSetCars: ConstraintLayout = findViewById(R.id.constraintLayoutpage2)
        val startButton: Button = findViewById(R.id.button4)

        selectedNumberTextView = findViewById(R.id.selected_number)
        val showDialogButton: Button = findViewById(R.id.show_dialog_button)
        val nextButton: Button = findViewById(R.id.next_button)
        showDialogButton.setOnClickListener {
            val dialog = NumberPickerDialog()
            dialog.valueChangeListener =
                NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
                    selectedNumberTextView.text = "Выбранное кол-во: $newVal"
                    nextButton.visibility = View.VISIBLE
                    countCars = newVal
                    println(countCars)
                }
            dialog.show(supportFragmentManager, "number_picker")
        }
        nextButton.setOnClickListener {
            constraintMain.visibility = View.GONE
            constraintSetCars.visibility = View.VISIBLE
            generateCars()
        }
        startButton.setOnClickListener {
            Toast.makeText(this, "Победитель ${race(autoCollection)}", Toast.LENGTH_LONG).show()
        }
    }

    fun generateCars() {
        if (countCurrentCars >= countCars){
            return
        }
        val constraintSetCars: ConstraintLayout = findViewById(R.id.constraintLayoutpage2)
        val consrtaintStart: ConstraintLayout = findViewById(R.id.ConsrtaintLayoutpage3)
        val spinner: Spinner = findViewById(R.id.spinner)
        val buttonNextCar: Button = findViewById(R.id.button3)
        val textMark: EditText = findViewById(R.id.markInput)
        val textModel: EditText = findViewById(R.id.modelinput)
        val textYear: EditText = findViewById(R.id.yearinput)
        val textPower: EditText = findViewById(R.id.powerInput)
        val textTimeto100: EditText = findViewById(R.id.accelerationInput)
        buttonNextCar.setOnClickListener {
            selectedType = spinner.selectedItem.toString()
            selectedMark = textMark.getText().toString()
            selectedModel = textModel.getText().toString()
            selectedYear = textYear.getText().toString().toInt()
            selectedPower = textPower.getText().toString().toInt()
            selectedTimeTo100 = textTimeto100.getText().toString().toDouble()
            val car = when (selectedType) {
                "Кроссовер" -> SuvAuto(
                    selectedMark,
                    selectedModel,
                    selectedYear,
                    selectedPower,
                    selectedTimeTo100
                )

                "Купе" -> Coupe(
                    selectedMark,
                    selectedModel,
                    selectedYear,
                    selectedPower,
                    selectedTimeTo100
                )

                "Седан" -> Sedan(
                    selectedMark,
                    selectedModel,
                    selectedYear,
                    selectedPower,
                    selectedTimeTo100
                )

                "Спорткар" -> Sportcar(
                    selectedMark,
                    selectedModel,
                    selectedYear,
                    selectedPower,
                    selectedTimeTo100
                )

                else -> Auto(
                    selectedMark,
                    selectedModel,
                    selectedYear,
                    selectedPower,
                    selectedTimeTo100
                )
            }
            autoCollection.add(car)
            countCurrentCars++
            if (countCurrentCars < countCars){
                textMark.text.clear()
                textModel.text.clear()
                textYear.text.clear()
                textPower.text.clear()
                textTimeto100.text.clear()
                generateCars()
            } else {
                constraintSetCars.visibility = View.GONE
                consrtaintStart.visibility = View.VISIBLE
            }
        }
    }
    fun race(autoCollection: MutableList<Auto>) : String {
        val numOfCars = 7
        var currentRound = autoCollection

        while (currentRound.size > 1) {
            val winners = mutableListOf<Auto>()
            val shuffledCars = currentRound.shuffled()
            for (i in shuffledCars.indices step 2) {
                if (i + 1 < shuffledCars.size) {
                    val car1 = shuffledCars[i]
                    val car2 = shuffledCars[i + 1]
                    val winner = if (car1.timeTo100 < car2.timeTo100) car1 else car2
                    winners.add(winner)
                    println("Гонка между $car1 и $car2, Победитель $winner")
                } else {
                    winners.add(shuffledCars[i])
                    println("${shuffledCars[i]} - Нет противника, следующий раунд")
                }
            }
            currentRound = winners
        }
        return currentRound.first().model
    }
}
open class Auto(
    val mark: String,
    val model: String,
    val productYear: Int,
    val enginePower: Int,
    val timeTo100: Double
){
    fun aboutCar(){
        Log.i("test","Марка - $mark+\n+Модель - $model+\n+Год выпуска - $productYear+\n+Мощность двигателя - $enginePower+\n+Время разгона до 100км/ч - $timeTo100")
    }
}
class SuvAuto(
    mark: String,
    model: String,
    productYear: Int,
    enginePower: Int,
    timeTo100: Double,
    val drive: String = "AWD"
) : Auto(mark,model,productYear,enginePower,timeTo100){}

class Coupe(
    mark: String,
    model: String,
    productYear: Int,
    enginePower: Int,
    timeTo100: Double,
    val doorCount: Int = 3
) : Auto(mark,model,productYear,enginePower,timeTo100){
}

class Sedan(
    mark: String,
    model: String,
    productYear: Int,
    enginePower: Int,
    timeTo100: Double,
    val seatsCount: Int = 4
) : Auto(mark,model,productYear,enginePower,timeTo100){
}

class Sportcar(
    mark: String,
    model: String,
    productYear: Int,
    enginePower: Int,
    timeTo100: Double,
    requiedFuel: Int = 100
) : Auto(mark,model,productYear,enginePower,timeTo100){}
