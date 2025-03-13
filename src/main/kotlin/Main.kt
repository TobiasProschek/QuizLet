import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.*

data class Questions(val Nr: Int, val Title: String, val Quest: String, val Answer: String)

fun main() {
    val quizRepo: QuizRepo = QuizRepoImpl()
    val gson = Gson()
    val file = File("src/main/kotlin/QuizQuests.json")
    val quiz: MutableList<Questions> = if (file.exists()) {
        val type = object : TypeToken<MutableList<Questions>>() {}.type
        gson.fromJson(file.readText(), type)
    } else {
        mutableListOf()
    }

    var points = 0
    var repeat = true
    while (repeat)  {
        val randomQuest: Int = (0..10).random()
        println("You have $points Points")
        println("Nr. ${quiz[randomQuest].Nr}")
        println("Title: ${quiz[randomQuest].Title}")
        println("Question: ${quiz[randomQuest].Quest}")

        print("Which answer you have? ")
        val answer = readln()

        if (answer.lowercase(Locale.getDefault()) == quiz[randomQuest].Answer){
            println("Your Answer $answer is True")
            points++
        } else {
            println("Your Answer $answer is False it was ${quiz[randomQuest].Answer}")
            points = 0
            println("You lose... do you wanna play more? ")
            val rePlay = readln().lowercase(Locale.getDefault())
            when (rePlay){
                "yes" -> repeat
                "no" -> repeat = false
                else -> repeat = false
            }
        }
    }
}