//
//  FetchedDataList.swift
//  iosApp
//
//  Created by Siddhant Dubey on 17/07/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct FetchedDataList: View {
    
    @StateObject private var viewModel = ViewModel()
    var receivedData: String
    
    let columns = [
        GridItem(alignment: .top)
    ]
    
    var body: some View {
        ZStack {
//            if !viewModel.objects.isEmpty {
                    ScrollView {
                        LazyVGrid(columns: columns, alignment: .leading, spacing: 20) {
                            ForEach(viewModel.responseData, id: \.self) { item in
                                NavigationLink(destination: DetailView(objectId: 1)) {
                                    ObjectFrame(obj: item, onClick: {})
                                }
                                .buttonStyle(PlainButtonStyle())
                            }
                        }
                        .onAppear(perform: {
                            viewModel.getAllContinents(fetchType: receivedData)
                        })
                        .padding(.horizontal)
                    }
//            }
//            else {
//                Text("No data available")
//            }
        }
    }
}


