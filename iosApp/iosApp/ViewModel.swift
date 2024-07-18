//
//  ViewModel.swift
//  iosApp
//
//  Created by Siddhant Dubey on 20/06/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class ViewModel: ObservableObject {
    @Published var responseData: [Continent] = []
    func getAllContinents(fetchType: String) {
        let continentsUseCase = KoinDependencies().continentsUseCase
        continentsUseCase.getContinentsData(fetchType: fetchType) { [self] state, error in
            let result = StateSwift(state!)
            switch result {
            case .loading:
                print("loading")
            case .success(let data):
                self.responseData = data
                print(responseData)
            case .error(let error):
                print(error)
            case nil:
                break
            }
        }
    }
}
