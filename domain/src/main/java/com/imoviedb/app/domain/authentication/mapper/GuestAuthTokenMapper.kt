import com.imoviedb.app.data.models.authentication.GuestAuthCreateTokenModel
import com.imoviedb.app.data.storage.authentication.GuestUserTokenEntity

fun GuestAuthCreateTokenModel.convertModelToEntity(): GuestUserTokenEntity{
    val input = this
    return GuestUserTokenEntity().apply {
        request_token = input.request_token ?: ""
        expiresAt = input.expiresAt
        status_code = input.status_code
        success = input.success
        status_message = input.status_message
    }
}

fun GuestUserTokenEntity.convertEntityToModel(): GuestAuthCreateTokenModel{
    val input = this
    return GuestAuthCreateTokenModel().apply {
        request_token = input.request_token
        expiresAt = input.expiresAt
        status_code = input.status_code
        success = input.success
        status_message = input.status_message
    }
}
