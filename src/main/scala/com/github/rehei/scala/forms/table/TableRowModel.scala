package com.github.rehei.scala.forms.table

trait TableRowModel {

  def identify(): String;
  
  def delete()
  
  def save()

}