package com.github.rehei.scala.forms.table

trait Handle[T <: AnyRef]  {
  def id: Long; 
  def resolve: T;
}