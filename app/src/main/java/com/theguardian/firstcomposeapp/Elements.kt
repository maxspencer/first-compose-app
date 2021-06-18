package com.theguardian.firstcomposeapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

sealed class Element

data class Paragraph(val text: String) : Element()
data class Heading(val text: String) : Element()

sealed class ParentElement : Element() {
    abstract val children: List<Element>
}

data class Row(override val children: List<Element>) : ParentElement()
data class Column(override val children: List<Element>) : ParentElement()
data class Button(
    val target: String,
    override val children: List<Element>
) : ParentElement()

fun column(func: ColumnBuilder.() -> Unit): Column {
    return ColumnBuilder().apply(func).build()
}

abstract class ParentElementBuilder<T> {
    internal val children = mutableListOf<Element>()

    fun p(text: String) {
        children.add(Paragraph(text))
    }

    fun h(text: String) {
        children.add(Heading(text))
    }

    fun row(func: RowBuilder.() -> Unit) {
        children.add(RowBuilder().apply(func).build())
    }

    fun column(func: ColumnBuilder.() -> Unit) {
        children.add(ColumnBuilder().apply(func).build())
    }

    fun button(target: String, func: ButtonBuilder.() -> Unit) {
        children.add(ButtonBuilder(target).apply(func).build())
    }

    abstract fun build(): T
}

class RowBuilder: ParentElementBuilder<Row>() {
    override fun build() = Row(children)
}

class ColumnBuilder: ParentElementBuilder<Column>() {
    override fun build() = Column(children)
}

class ButtonBuilder(private val target: String): ParentElementBuilder<Button>() {
    override fun build() = Button(target, children)
}

@Composable
fun ElementComponent(element: Element, navController: NavController) {
    when(element) {
        is Paragraph -> ParagraphComponent(element)
        is Heading -> Text(element.text, style = MaterialTheme.typography.h6)
        is Button -> Button(onClick = { navController.navigate(element.target) }) {
            element.children.forEach {
                ElementComponent(it, navController)
            }
        }
        is Row -> RowComponent(element, navController)
        is Column -> ColumnComponent(element, navController)
    }
}

@Composable
fun ParagraphComponent(element: Paragraph) {
    Text(element.text)
}

@Composable
fun RowComponent(element: Row, navController: NavController) {
    Row {
        element.children.forEach {
            ElementComponent(it, navController)
        }
    }
}

@Composable
fun ColumnComponent(element: Column, navController: NavController) {
    Column {
        element.children.forEach {
            ElementComponent(it, navController)
        }
    }
}
