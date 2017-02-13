package com.github.rehei.scala.forms.table

trait HandleFactory[T <: AnyRef] {
  def createHandle(model: T): Handle[T];
}