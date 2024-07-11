//
//  EnumCreation.swift
//  iosApp
//
//  Created by Siddhant Dubey on 26/06/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

enum StateSwift {
    case loading
    case success(data: [Continent])
    case error(errorCode: String)
}

extension StateSwift {
    init?(_ value: States<NSArray>) {
        switch value {
        case is StatesLoading:
            self = .loading
        case let success as StatesSuccess<AnyObject>:
            self = .success(data: success.data as! [Continent])
        case let error as StatesError:
            self = .error(errorCode: error.debugDescription)
        default:
            return nil
        }
    }
}

//func returnValueTest() {
//    if let result = ReturnValueSwift(ReturnValue.Loading.shared) {
//        switch result {
//        case .loading:
//            print("loading")
//        case let .success(data):
//            print("success \(data)")
//        case let .error(errorCode):
//            print("error \(errorCode)")
//            
//        }
//    }
//}
