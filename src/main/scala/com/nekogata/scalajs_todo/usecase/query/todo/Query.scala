package com.nekogata.scalajs_todo.usecase.query.todo

import com.nekogata.scalajs_todo.domain.TodoRepository
import com.nekogata.scalajs_todo.presentation.TodoPresentation

import scala.scalajs.js

trait Query {
  protected val repository: TodoRepository

  def all() = {
    val todos = repository.all().map(t => new TodoPresentation(t).asJs())
    js.Array(todos: _*)
  }
}
