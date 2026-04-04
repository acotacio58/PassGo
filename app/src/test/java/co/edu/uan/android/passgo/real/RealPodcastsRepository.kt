package co.edu.uan.android.passgo.real

import co.edu.uan.android.passgo.data.model.Podcast
import co.edu.uan.android.passgo.data.repository.PodcastsRepository
import co.edu.uan.android.passgo.data.network.ListenNotesApiService
import co.edu.uan.android.passgo.fake.FakeDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RealPodcastsRepository: PodcastsRepository {
    override suspend fun getPodcasts(): List<Podcast> {
        try {
            val service = Retrofit.Builder()

                .baseUrl("https://listen-api.listennotes.com/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ListenNotesApiService::class.java)
            val data = service.bestPodcasts()
            println(data)
            return data.podcasts
        }
        catch (e: Exception) {
            throw RuntimeException(e)
            return FakeDataSource.podcastsList.podcasts
        }
    }
}