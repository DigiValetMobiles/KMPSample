import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
//                    single<AppDatabase> { createRoomDatabase(get()) }
        KoinKt.doInitKoin()
        DataStore_iOSKt.createDataStore()
    }
    
    var body: some Scene {
        WindowGroup {
            ListView()            
        }
    }
}
