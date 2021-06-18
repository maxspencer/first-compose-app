package com.theguardian.firstcomposeapp

import androidx.navigation.NavBackStackEntry


val routes = mapOf<String, (NavBackStackEntry) -> Element>(
    "headlines" to {
        column {
            p("Headlines")
            row {
                column {
                    h("Title 1")
                    p("Byline 1")
                    button("articles/1") {
                        p("Read")
                    }
                }
                column {
                    h("Title 2")
                    p("Byline 2")
                    button("articles/2") {
                        p("Read")
                    }
                }
            }
        }
    },
    "articles/{articleId}" to { entry ->
        articles[entry.arguments?.getString("articleId")]!!
    }
)

val articles = mapOf<String, Element> (
    "1" to column {
        h("Title 1")
        row {
            p("Byline 1")
            p("01/01/2021 11:11 BST")
        }
        p("Something happened")
    },
    "2" to column {
        h("Title 2")
        row {
            p("Byline 2")
            p("02/02/2021 12:12 BST")
        }
        p("Something else happened as well")
    },
)