package ktorwebapi.helpers

class JsonResponse<T>(val ok: Boolean, val data: T?, val error: Any?)
{
    companion object {
        fun<T> success(data: T): JsonResponse<T>
        {
            return JsonResponse(true, data, null)
        }

        fun<T> failure(error: Any): JsonResponse<T>
        {
            return JsonResponse(false, null, error)
        }
    }
}
