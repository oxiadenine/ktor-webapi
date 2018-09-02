package ktorwebapi.helpers

data class Response(val ok: Boolean, val data: Any? = null, val error: Any? = null)

object JsonResponse {
    fun <T> success(data: T) = Response(true, data = data)
    fun <T> failure(error: T) = Response(false, error = error)
}
