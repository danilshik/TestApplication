package com.example.test.orderCreateNew

import androidx.lifecycle.SavedStateHandle
import com.example.test.IViewModelState
import com.example.test.Model.OrderItemAddress

//sealed class OrderCreateNewState : IViewModelState {
//    object Loading : OrderCreateNewState()
//}

data class OrderCreateNewState(
    val isCarSettingsLoading : Boolean = false,
    var orderCreateModel: OrderCreateNewModel = OrderCreateNewModel()
) : IViewModelState {

}


//data class ArticleState(
//    val isAuth: Boolean = false,
//    val isLoadingContent: Boolean = true,
//    val isLoadingReviews: Boolean = true,
//    val isLike: Boolean = false,
//    val isBookmark: Boolean = false,
//    val isShowMenu: Boolean = false, // Отображается в меню
//    val isBigText: Boolean = false, // Шрифт увеличен
//    val isDarkMode: Boolean = false, // Темный режим
//    val isSearch: Boolean = false, // В режиме поиска
//    val searchQuery: String? = null,
//    val searchResults: List<Pair<Int, Int>> = emptyList(), // Результаты поиска (стартовая и конечная позиция)
//    val searchPosition: Int = 0, // Текущая позиция найденного результата
//    val shareLink: String? = null, // Ссылка share
//    val title: String? = null,
//    val category: String? = null,
//    val categoryIcon: Any? = null,
//    val date: String? = null,
//    val author: Any? = null,
//    val poster: String? = null,
//    val content: List<MarkdownElement> = emptyList(),
//    val commentsCount: Int = 0,
//    val answerTo: String? = null,
//    val answerToMessageId: String? = null,
//    val showBottomBar: Boolean = true,
//    val commentText: String? = null,
//    val hashtags: List<String> = emptyList(),
//    val source: String? = null
//): IViewModelState {
//    override fun save(outState: SavedStateHandle) {
//        outState.set("isSearch", isSearch)
//        outState.set("searchQuery", searchQuery)
//        outState.set("searchResults", searchResults)
//        outState.set("searchPosition", searchPosition)
//        outState.set("commentText", commentText)
//        outState.set("answerTo", answerTo)
//        outState.set("answerToSlug", answerToMessageId)
//    }
//
//    override fun restore(savedState: SavedStateHandle): ArticleState {
//        return copy(
//            isSearch = savedState["isSearch"] ?: false,
//            searchQuery = savedState["searchQuery"],
//            searchResults = savedState["searchResults"] ?: emptyList(),
//            searchPosition = savedState["searchPosition"] ?: 0,
//            commentText = savedState["commentText"],
//            answerTo = savedState["answerTo"],
//            answerToMessageId = savedState["answerToSlug"]
//        )
//    }
//}