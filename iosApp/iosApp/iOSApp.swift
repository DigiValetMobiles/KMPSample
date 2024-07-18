import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
//                    single<AppDatabase> { createRoomDatabase(get()) }
        KoinKt.doInitKoin()
        DataStore_iosKt.createDataStore()
    }
    
    var body: some Scene {
        WindowGroup {
            ListView()            
        }
    }
}
