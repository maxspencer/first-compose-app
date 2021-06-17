package com.theguardian.firstcomposeapp

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