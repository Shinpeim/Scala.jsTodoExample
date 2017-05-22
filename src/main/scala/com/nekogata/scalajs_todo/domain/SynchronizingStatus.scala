package com.nekogata.scalajs_todo.domain

sealed trait SynchronizingStatus

case object Synchronized extends SynchronizingStatus
case object Synchronizing extends SynchronizingStatus
case object SynchronizingFailed extends SynchronizingStatus
