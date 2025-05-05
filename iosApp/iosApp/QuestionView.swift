import SwiftUI
import shared

extension QuestionView {
    class ViewModel: ObservableObject {

        var onScore: (QuizScore) -> Void

        @Published var currentQuestion: QuestionVmo? = nil
        @Published var score: QuizScore? = nil

        var iosViewModel = IosQuestionViewModel.shared.self

        init(onScore: @escaping (QuizScore) -> Void) {
            self.onScore = onScore

            Task { @MainActor in
                iosViewModel.onNewQuestion { question in
                    self.updateQuestion(question: question)
                }
                iosViewModel.onScore { score in
                    self.score = score
                }

                iosViewModel.fetch()
            }
        }

        func updateQuestion(question: QuestionVmo?) {
            self.currentQuestion = question
        }

        func answerQuestion(isCorrect: Bool) {
            iosViewModel.answer(isCorrect: isCorrect)
          
            Task {
                try await Task.sleep(for: .seconds(1))
                print(score?.toJson() ?? "No score")
                onScore(score!)
            }
        }
    }
}

extension AnswerVmo: @retroactive Identifiable {
    public var id: Int { self.text.hashValue }
}

struct QuestionView: View {

    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        let question = viewModel.currentQuestion

        VStack(spacing: 8) {
            if question == nil {
                Text("Loading...")
            } else {
                Text(question?.text ?? "")
                ForEach(question!.answers) { answer in
                    Button(action: {
                        self.viewModel.answerQuestion(
                            isCorrect: answer.isCorrect
                        )
                    }) {
                        Text(answer.text)
                    }
                    .padding()
                }
            }
        }
        .padding(.all, 20)

    }
}

struct QuestionView_previews: PreviewProvider {
    static var previews: some View {
        QuestionView(viewModel: QuestionView.ViewModel(onScore: { score in print(score)}))
    }
}
