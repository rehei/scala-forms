package com.github.rehei.scala.forms.table

trait TableRowModel {

  def id(): java.lang.Long;

  def save;

  def isInsertRequired: Boolean;

  def delete;

}