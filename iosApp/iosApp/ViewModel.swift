//
//  ViewModel.swift
//  iosApp
//
//  Created by Siddhant Dubey on 20/06/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

var responseData: [ContinentsQuery.Continent] = []
func getAllContinents() {
    let xyz = KoinDependencies().countriesRepository
    xyz.getContinents { state, error in
        let result = StateSwift(state!)
        switch result {
        case .loading:
            print("loading")
        case .success(let data):
            responseData = data
            print(responseData)
        case .error(let error):
            print(error)
        case nil:
            break
        }
    }
}
