package uz.devmi.rortyexperience.core.data.datasource.remote

import retrofit2.http.GET
import retrofit2.http.Query
import uz.devmi.rortyexperience.core.data.datasource.remote.model.CharacterResponse
import uz.devmi.rortyexperience.core.data.datasource.remote.model.PageableResponse

interface Webservice {

    companion object {
        const val CHARACTER = "character"
    }

    @GET(CHARACTER)
    suspend fun getCharactersByPage(@Query("page") page: Int): PageableResponse<CharacterResponse>

}