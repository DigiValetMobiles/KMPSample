import SwiftUI
import Shared

struct ListView: View {
//    @StateViewModel
//    var viewModel = ListViewModel(
//        countriesRepository: KoinDependencies().countriesRepository
//    )
    
    @StateObject private var viewModel = ViewModel()
    
    let columns = [
        GridItem(.adaptive(minimum: 120), alignment: .top)
    ]

    var body: some View {
        ZStack {
//            if !viewModel.objects.isEmpty {
                NavigationStack {
                    ScrollView {
                        LazyVGrid(columns: columns, alignment: .leading, spacing: 20) {
                            Button("API CALL") {
                                viewModel.getAllContinents()
                            }
                            ForEach(viewModel.responseData, id: \.self) { item in
                                NavigationLink(destination: DetailView(objectId: 1)) {
                                    ObjectFrame(obj: item, onClick: {})
                                }
                                .buttonStyle(PlainButtonStyle())
                            }
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

                Text(obj.code ?? "")
                .font(.headline)

            Text(obj.name ?? "")
                .font(.subheadline)

                Text("Countries: \(String(obj.countries?.count ?? 0))")
                .font(.caption)
        }
    }
}
