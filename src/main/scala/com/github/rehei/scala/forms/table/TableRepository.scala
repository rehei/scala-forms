package com.github.rehei.scala.forms.table

trait TableRepository[T <: TableRowModel] extends HandleFactory[T] {
  
  def single(id: Long): Option[T]
  
  def create(): T
  
}