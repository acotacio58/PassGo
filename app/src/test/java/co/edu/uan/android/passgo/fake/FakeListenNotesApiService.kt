package co.edu.uan.android.passgo.fake

import co.edu.uan.android.passgo.data.model.PodcastResponse
import co.edu.uan.android.passgo.data.network.ListenNotesApiService

class FakeListenNotesApiService: ListenNotesApiService {
    override suspend fun bestPodcasts(): PodcastResponse {
        return FakeDataSource.podcastsList
    }
}