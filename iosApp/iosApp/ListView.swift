import SwiftUI
import Shared

struct ListView: View {
//    @StateViewModel
//    var viewModel = ListViewModel(
//        countriesRepository: KoinDependencies().countriesRepository
//    )
    
    @StateObject private var viewModel = ViewModel()
    
    @State private var dataToSend: String = "GraphQl"
    
    struct FetchDataOptions: Identifiable {
        var id: String {
            self.heading
        }
        var heading: String
        var fetchType: String
    }
    
    let fetchDataOptions = [
        FetchDataOptions(heading: "Fetch from network via KTOR",
                         fetchType: FetchType.ktor.value),
        FetchDataOptions(heading: "Fetch from network via graphql", 
                         fetchType: FetchType.graphql.value),
        FetchDataOptions(heading: "Fetch from graphql cache", 
                         fetchType: FetchType.graphqlcaching.value),
        FetchDataOptions(heading: "Fetch from room", 
                         fetchType: FetchType.roomcaching.value),
        FetchDataOptions(heading: "Fetch from dataStorePreferences", 
                         fetchType: FetchType.datastorepreferencescaching.value),
        FetchDataOptions(heading: "Clear All Cache", 
                         fetchType: "")
    ]
    
    let columns = [
        GridItem(alignment: .top)
    ]

    var body: some View {
        ZStack {
//            if !viewModel.objects.isEmpty {
                NavigationStack {
                    ScrollView {
                        LazyVGrid(columns: columns, alignment: .center, spacing: 20) {
                            ForEach(fetchDataOptions) { option in
                                NavigationLink(destination: FetchedDataList(receivedData: option.fetchType)) {
                                    Text(option.heading)
                                        .bold()
                                        .foregroundStyle(Color.mint)
                                        .padding()
                                        .frame(maxWidth: .infinity)
                                        .background(
                                            .black
                                        )
                                        .cornerRadius(30)
                                }
//                                Button(action: {
//                                    viewModel.getAllContinents(fetchType: option.fetchType)
//                                }, label: {
//                                    Text(option.heading)
//                                        .bold()
//                                        .foregroundStyle(Color.mint)
//                                        .padding()
//                                        .frame(maxWidth: .infinity)
//                                        .background(
//                                            .black
//                                        )
//                                        .cornerRadius(30)
//                                })
                            }
//                            ForEach(viewModel.responseData, id: \.self) { item in
//                                NavigationLink(destination: DetailView(objectId: 1)) {
//                                    ObjectFrame(obj: item, onClick: {})
//                                }
//                                .buttonStyle(PlainButtonStyle())
//                            }
                        }
                        .padding(.horizontal)
                    }
                }
//            } 
//            else {
//                Text("No data available")
//            }
        }
    }
}

struct ObjectFrame: View {
    let obj: Continent
    let onClick: () -> Void

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
//            GeometryReader { geometry in
//                AsyncImage(url: URL(string: obj.primaryImageSmall)) { phase in
//                    switch phase {
//                    case .empty:
//                        ProgressView()
//                            .frame(width: geometry.size.width, height: geometry.size.width)
//                    case .success(let image):
//                        image
//                            .resizable()
//                            .scaledToFill()
//                            .frame(width: geometry.size.width, height: geometry.size.width)
//                            .clipped()
//                            .aspectRatio(1, contentMode: .fill)
//                    default:
//                        EmptyView()
//                            .frame(width: geometry.size.width, height: geometry.size.width)
//                    }
//                }
//            }
//            .aspectRatio(1, contentMode: .fit)

            Text(obj.code)
                .font(.headline)

            Text(obj.name ?? "")
                .font(.subheadline)

                Text("Countries: \(String(obj.countries?.count ?? 0))")
                .font(.caption)
        }
    }
}
