package space.stanton.know.presentation

import space.stanton.know.data.QuestionDto

fun QuestionDto.toVmo() = QuestionVmo(
    text = question,
    answers = listOfNotNull(
        answers.answer_a?.let { it to correct_answers.answer_a_correct },
        answers.answer_b?.let { it to correct_answers.answer_b_correct },
        answers.answer_c?.let { it to correct_answers.answer_c_correct },
        answers.answer_d?.let { it to correct_answers.answer_d_correct },
        answers.answer_e?.let { it to correct_answers.answer_e_correct },
        answers.answer_f?.let { it to correct_answers.answer_f_correct },
    ).map { (answer, answerCorrect) ->
        AnswerVmo(
            text = answer,
            isCorrect = answerCorrect
        )
    }
)