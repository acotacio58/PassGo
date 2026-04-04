package co.edu.uan.android.passgo.fake

import co.edu.uan.android.passgo.data.model.Podcast
import co.edu.uan.android.passgo.data.repository.PodcastsRepository

class FakePodcastsRepository: PodcastsRepository {
    override suspend fun getPodcasts(): List<Podcast> {
        return FakeDataSource.podcastsList.podcasts
    }
}