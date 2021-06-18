package com.theguardian.firstcomposeapp

val data = NewsFront(
    children = listOf(
        ArticleGroup(
            title = "Headlines",
            articles = listOf(Article(
                title = "Orchid thought to be extinct in UK found on roof of London bank",
                byline = "Rhi Storer",
                date = "Thu 17 Jun 2021 12.49 BST"
            ))
        ),
        Thrasher(message = "Thrashers are named after Steven Thrasher")
    )
)

data class NewsFront(
    val children: List<NewsFrontChild>
)

sealed class NewsFrontChild

data class ArticleGroup(
    val title: String,
    val articles: List<Article>
): NewsFrontChild()

data class Thrasher(
    val message: String
): NewsFrontChild()

data class Article(
    val title: String,
    val byline: String,
    val date: String
)