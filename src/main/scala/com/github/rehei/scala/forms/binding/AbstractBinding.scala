package com.github.rehei.scala.forms.binding

import scala.collection.mutable.ListBuffer

import com.github.rehei.scala.forms.Field
import com.github.rehei.scala.forms.decorators.FieldDecorator
import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.decorators.LabelDecorator
import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.util.ReflectUtil
import scala.xml.NodeSeq
import scala.xml.Text
import scala.language.existentials
import scala.reflect.runtime.universe._
import com.github.rehei.scala.forms.decorators.FieldDecorator
import com.github.rehei.scala.forms.decorators.LabelDecorator

abstract class AbstractBinding[X <: AbstractBinding[_]] {

  private var decorators: Map[Type, FieldDecorator] = Map[Type, FieldDecorator]()

  protected def injectDecorators(injectable: Map[Type, FieldDecorator]) {
    this.decorators = injectable
  }

  def bind[T](context: Field, model: AnyRef, markupFactory: MarkupFactory[T]): T

  def label() = {
    getDecorator[LabelDecorator].map(_.label)
  }

  def label(label: String) = {
    this.decorateWith(LabelDecorator(label))
  }

  def decorateWith[T <: FieldDecorator](decorator: T)(implicit tag: TypeTag[T]): X = {
    val keyValue = (tag.tpe, decorator)
    val newDecorators = decorators + keyValue

    val copy = ReflectUtil.create(this.getClass()).asInstanceOf[X]
    copy.injectDecorators(newDecorators)
    copy
  }

  def getDecorator[T <: FieldDecorator](implicit tag: TypeTag[T]): Option[T] = {
    decorators.get(tag.tpe).map { _.asInstanceOf[T] }
  }

}