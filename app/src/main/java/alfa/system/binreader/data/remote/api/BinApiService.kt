package alfa.system.binreader.data.remote.api

import alfa.system.binreader.data.remote.dto.BinInfoDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

// интерфейс для описания http-запроса.
// подставляет значение {bin} в конец запроса по пути @Path.
// Возвращает BinInfoDto - DTO парсинг из Json
interface BinApiService {
    @Headers("Accept-Version: 3")
    @GET("{bin}")
    suspend fun getBinInfo(@Path("bin") bin: String): BinInfoDto
}