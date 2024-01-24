package android.com.baseapp.data.api.dataResponse

data class QuoteList(
    val count: Int = 0,
    val lastItemIndex: Int = 0,
    val page: Int = 0,
    val results: List<Result> = ArrayList(),
    val totalCount: Int = 0,
    val totalPages: Int = 0
)

data class Result(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)