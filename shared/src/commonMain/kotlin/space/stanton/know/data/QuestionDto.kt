@file:Suppress("PropertyName")

package space.stanton.know.data

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDto(
    val id: Int,
    val question: String,
    val answers: AnswerSet,
    val multiple_correct_answers: Boolean,
    val correct_answers: CorrectAnswerBooleans,
    val explanation: String?,
    val category: String?,
    val tags: List<Tag>,
    val difficulty: String?,
)

@Serializable
data class AnswerSet(
    val answer_a: String?,
    val answer_b: String?,
    val answer_c: String?,
    val answer_d: String?,
    val answer_e: String?,
    val answer_f: String?
)

@Serializable
data class CorrectAnswerBooleans(
    val answer_a_correct: Boolean,
    val answer_b_correct: Boolean,
    val answer_c_correct: Boolean,
    val answer_d_correct: Boolean,
    val answer_e_correct: Boolean,
    val answer_f_correct: Boolean,
)

@Serializable
data class Tag(
    val name: String
)





