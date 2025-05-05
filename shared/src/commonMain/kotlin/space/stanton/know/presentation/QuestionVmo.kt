package space.stanton.know.presentation

data class QuestionVmo(
    val text: String,
    val answers: List<AnswerVmo>
)