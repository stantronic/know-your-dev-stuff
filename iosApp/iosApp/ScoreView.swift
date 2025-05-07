import UIKit
import SwiftUI
import shared

//
//  ScoreView.swift
//  iosApp
//
//  Created by Steven stanton on 07/05/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

struct ComposeView : UIViewControllerRepresentable {
    var score: QuizScore
    
    var toStart: () -> Void
    
    func makeUIViewController(context: Context) -> UIViewController {
        ComposeControllerKt.ComposeController(score: score, toStart:  toStart)
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context){
        
    }
}


struct ScoreView: View {
    
    var score: QuizScore
    
    var toStart: () -> Void
    
    
    var body : some View {
        ComposeView(score: score, toStart: toStart).ignoresSafeArea(.keyboard)
    }
}
