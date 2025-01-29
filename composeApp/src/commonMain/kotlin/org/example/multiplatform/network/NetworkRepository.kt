package network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import org.example.multiplatform.models.MealsCategoriesResponse

class NetworkRepository(private val httpClient: HttpClient) {

     fun getProductList(): Flow<NetWorkResult<MealsCategoriesResponse?>> {
        return toResultFlow {
                val response = httpClient.get("https://www.themealdb.com/api/json/v1/1/categories.php").body<MealsCategoriesResponse>()
                 NetWorkResult.Success(response)
        }
    }


}