package com.github.rehei.scala.forms.table

object NullTableRepository extends TableRepository[TableRowModel] {

  def single(id: String): Option[TableRowModel] = ???

  def create(): TableRowModel = ???
  
  def delete(id: String) = ???
  
  def list() = List.empty

}