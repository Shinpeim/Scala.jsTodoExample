package com.nekogata.scalajs_todo.domain

sealed trait SynchronizingStatus

case object CreatedLocally extends SynchronizingStatus
case object UpdatedLocally extends SynchronizingStatus
case object Synchronized extends SynchronizingStatus
case object Synchronizing extends SynchronizingStatus
