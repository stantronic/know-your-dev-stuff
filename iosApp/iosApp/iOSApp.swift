import SwiftUI
import shared

enum Page :Hashable{
    case welcome
    case questions
    case score(Int)
}

@main
struct iOSApp: App {

    @State private var path: [Page] = []
    
    @State private var score: QuizScore? = nil

    var body: some Scene {

        WindowGroup(content: {
          NavigationStack(path: $path) {
                VStack(spacing: 20) {
                    
                    Text("Know your stuff")
                    
                    Button(
                        action: {
                            path.append(.questions)
                        },
                        label: {
                            Text("Begin")
                        },
                    )
                }
                .navigationDestination(
                    for: Page.self,
                    destination: { page in
                        
                        switch page {
                        case .welcome: Text("Welcome")
                        case .questions:
                            QuestionView(
                                viewModel: QuestionView.ViewModel(
                                    onScore: { score in
                                        let value = Int(score.score)
                                        path.append(.score(value))
                                    }
                                )
                            )
                        case .score(let value):
                            ScoreView(
                                score: QuizScore(
                                score: Int32(value),
                                total: 10,
                                complete: true
                            ), toStart: {
                                path.removeAll()
                            })
                        }
                    }
                )
            }.onAppear {
                DiModuleKt.insertKoin()
            }
        })
    }
}
