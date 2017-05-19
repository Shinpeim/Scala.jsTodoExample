package com.nekogata.scalajs_todo.service

import com.nekogata.scalajs_todo.command.AddTodoCommand
import com.nekogata.scalajs_todo.domain.TodoRepository
import com.nekogata.scalajs_todo.infrastracture.InMemoryTodoRepository
import com.nekogata.scalajs_todo.presentation.TodoPresentation

import scala.scalajs.js
import scala.scalajs.js.Dictionary
import scala.scalajs.js.annotation.{JSExportAll, JSExportTopLevel}

@JSExportAll
@JSExportTopLevel("TodoService")
class TodoServiceImpl extends TodoService {
  protected val repository: TodoRepository = new InMemoryTodoRepository

  //////////////
  // commands //
  //////////////
  val addCommand: AddTodoCommand = new AddTodoCommand
  override def executeAddCommand(): Unit = super.executeAddCommand()

  /////////////
  // queries //
  /////////////
  override def fetchTodos(): js.Array[Dictionary[Any]] = super.fetchTodos()
}
