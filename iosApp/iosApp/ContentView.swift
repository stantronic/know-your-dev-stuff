import SwiftUI
import shared



extension ContentView {
    class ViewModel: ObservableObject {
        
        @Published var text = "Loading..."
    
        
        let queue = DispatchQueue.global(qos: .background)
        
        func updateGreeting(_ greeting: String){
            text = greeting
        }
        
        init() {
            Task {
                do{
                    
                    let greeting = try await Greeting().greet()
                        
                    await MainActor.run {
                        self.updateGreeting(greeting)
                    }
                } catch {
                    print(error)
                }
            }
        }
    }
}


struct ContentView: View {

	 @ObservedObject private(set) var viewModel: ViewModel

	var body: some View {
		Text(viewModel.text)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
        ContentView(viewModel: ContentView.ViewModel())
	}
}
