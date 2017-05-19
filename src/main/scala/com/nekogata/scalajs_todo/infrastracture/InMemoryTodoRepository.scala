package com.nekogata.scalajs_todo.infrastracture

import com.nekogata.scalajs_todo.domain.{Todo, TodoRepository}

class InMemoryTodoRepository extends TodoRepository{
  private var todos: Seq[Todo] = Seq.empty

  def store(todo: Todo) = todos = todos ++ Seq(todo)

  def all = todos
}
