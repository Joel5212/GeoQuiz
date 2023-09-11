import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.geoquiz2.Question
import com.example.geoquiz2.R

private const val TAG = "QuizViewModel"

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false)
    )

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    private var amountOfQuestions = questionBank.size

    private var amountOfCorrectAnswers = 0

    private var finished = false

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val questionAnswered : Boolean
        get() = questionBank[currentIndex].answered

    val getAmountOfQuestions: Int
        get() = amountOfQuestions

    val getAmountOfCorrectAnswers: Int
        get() = amountOfCorrectAnswers

    val checkFinished: Boolean
        get() = finished

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious(){
        if(currentIndex > 0)
        {
            currentIndex--;
        }
        else
        {
            currentIndex = questionBank.size-1
        }
    }

    fun incrementAmountOfCorrectAnswers()
    {
        amountOfCorrectAnswers++
    }

    fun changeAnsweredToTrue()
    {
        questionBank[currentIndex].answered = true
    }

    fun checkIfGameFinished()
    {
        finished=true
        for(questions in questionBank)
        {
            if(!questions.answered)
            {
                finished = false
                break
            }
        }
    }

    }
