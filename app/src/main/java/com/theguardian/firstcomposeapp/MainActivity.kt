package com.theguardian.firstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        setContent {
            MaterialTheme {
                FrontView(data)
            }
        }
    }
}

@Composable
fun FrontView(front: NewsFront) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        front.children.forEach {
            when(it) {
                is ArticleGroup -> ArticleGroupView(it)
                is Thrasher -> ThrasherView(it)
            }
        }
    }
}

@Composable
fun ArticleGroupView(articleGroup: ArticleGroup) {
    Column {
        ArticleGroupHeading(title = articleGroup.title)
        articleGroup.articles.forEach {
            ArticleCard(it)
        }
    }
}

@Composable
fun ArticleCard(article: Article) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(article.title, style = MaterialTheme.typography.h6)
        Text(article.byline, style = MaterialTheme.typography.body2)
        Text(article.date, style = MaterialTheme.typography.body2)
    }
}

@Preview
@Composable
fun NewsArticleCardPreview() {
    ArticleCard(
        Article(
            title = "Orchid thought to be extinct in UK found on roof of London bank",
            byline = "Rhi Storer",
            date = "Thu 17 Jun 2021 12.49 BST"
        )
    )
}

@Composable
fun ArticleGroupHeading(title: String) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Divider()
        Text(text = title, style = MaterialTheme.typography.h4)
    }
}

@Preview
@Composable
fun ArticleGroupHeadingPreview() {
    ArticleGroupHeading(title = "Headlines")
}

@Composable
fun ThrasherView(thrasher: Thrasher) {
    Surface(color = Color.Yellow) {
        Text(
            text = thrasher.message,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h5
        )
    }
}

@Preview
@Composable
fun ThrasherViewPreview() {
    ThrasherView(thrasher = Thrasher("Hello world!"))
}