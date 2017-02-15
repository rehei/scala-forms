package com.github.rehei.scala.forms.table

trait TableRepository[T <: TableRowModel] {
  
  def single(id: String): Option[T]
  
  def create(): T
  
  def delete(id: String)
  
  def list(): List[T]
}