import SwiftUI
import shared


extension ContentView {
    class ViewModel: ObservableObject {
        
        @Published var currentQuestion : QuestionVmo? = nil
        
        var iosViewModel = IosQuestionViewModel.shared.self
    
        init(){
            DiModuleKt.insertKoin()
           
            Task { @MainActor in
                iosViewModel.onNewQuestion { question in
                    self.updateQuestion(question: question)
                }
                iosViewModel.fetch()
            }
        }

        func updateQuestion(question: QuestionVmo?){
                
            self.currentQuestion = question
        }
        
        
        func answerQuestion(isCorrect: Bool){
            iosViewModel.answer(isCorrect: isCorrect)
        }
    }
}

extension AnswerVmo :@retroactive Identifiable {
    public var id: Int { self.text.hashValue }
}


struct ContentView: View {

	 @ObservedObject private(set) var viewModel: ViewModel

	var body: some View {
        let question = viewModel.currentQuestion
        
        VStack (spacing: 8){
            if question == nil {
                Text("Loading...")
            } else {
                Text(question?.text ?? "")
                ForEach(question!.answers) { answer in
                    Button(action: {
                        self.viewModel.answerQuestion(isCorrect: answer.isCorrect)
                    }) {
                        Text(answer.text)
                    }
                    .padding()
                }
            }
        }
        .padding(
            .all, 20
        )
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView(viewModel: ContentView.ViewModel())
	}
}
