package com.nekogata.scalajs_todo.infrastracture

import com.nekogata.scalajs_todo.domain.{Todo, TodoRepository}

class InMemoryTodoRepository extends TodoRepository{
  def store(todo: Todo) = InMemoryTodoRepository.todos = InMemoryTodoRepository.todos ++ Seq(todo)

  def all = InMemoryTodoRepository.todos
}

object InMemoryTodoRepository {
  private var todos: Seq[Todo] = Seq.empty
}
