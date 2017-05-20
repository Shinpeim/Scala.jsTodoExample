package com.nekogata.scalajs_todo.infrastracture

import com.nekogata.scalajs_todo.domain.{Todo, TodoRepository}
import com.nekogata.scalajs_todo.js_bridge.TodoRepositoryChanged

class InMemoryTodoRepository extends TodoRepository{
  def nextId() = {
    InMemoryTodoRepository.nextId += 1
    InMemoryTodoRepository.nextId
  }

  def find(id: Int) = InMemoryTodoRepository.todos.find(_.id == id)

  def store(todo: Todo) = {
    val companion = InMemoryTodoRepository
    find(todo.id) match {
      case Some(t) => {
        companion.todos = companion.todos.filterNot(_.id == t.id) ++ Seq(todo)
      }
      case None => {
        companion.todos = companion.todos ++ Seq(todo)
      }
    }
    TodoRepositoryChanged.fire()
  }

  def all = InMemoryTodoRepository.todos
}

object InMemoryTodoRepository {
  private var nextId:Int = 0;
  private var todos: Seq[Todo] = Seq.empty
}
