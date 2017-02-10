package com.github.rehei.scala.forms.binding

import scala.collection.mutable.ListBuffer

import com.github.rehei.scala.forms.Bindable
import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.decorators.LabelDecorator
import com.github.rehei.scala.forms.markup.MarkupFactory
import com.github.rehei.scala.forms.util.ReflectUtil
import scala.xml.NodeSeq
import scala.xml.Text
import scala.language.existentials
import scala.reflect.runtime.universe._
import com.github.rehei.scala.forms.decorators.LabelDecorator

abstract class AbstractBinding[X <: AbstractBinding[_]] {

  private var decorators: Map[Type, AnyRef] = Map[Type, AnyRef]()

  protected def injectDecorators(injectable: Map[Type, AnyRef]) {
    this.decorators = injectable
  }

  def bind[T](context: Bindable, model: AnyRef, markupFactory: MarkupFactory[T]): T

  def label() = {
    getDecorator[LabelDecorator].map(_.label)
  }

  def label(label: String) = {
    this.decorateWith(LabelDecorator(label))
  }

  def decorateWith[T <: AnyRef](decorator: T)(implicit tag: TypeTag[T]): X = {
    val keyValue = (tag.tpe, decorator)
    val newDecorators = decorators + keyValue

    val copy = ReflectUtil.create(this.getClass()).asInstanceOf[X]
    copy.injectDecorators(newDecorators)
    copy
  }

  def getDecorator[T <: AnyRef](implicit tag: TypeTag[T]): Option[T] = {
    decorators.get(tag.tpe).map { _.asInstanceOf[T] }
  }

}