package com.github.rehei.scala.forms.binding

import scala.collection.mutable.ListBuffer

import com.github.rehei.scala.forms.BindableComponent
import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import com.github.rehei.scala.forms.decorators.LabelDecorator
import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import com.github.rehei.scala.forms.util.ReflectUtil
import scala.xml.NodeSeq
import scala.xml.Text
import scala.language.existentials
import scala.reflect.runtime.universe._
import com.github.rehei.scala.forms.decorators.LabelDecorator
import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable

trait Decoratable[X <: Decoratable[_]] {

  private var decorators: Map[Type, AnyRef] = Map[Type, AnyRef]()

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

  protected def injectDecorators(injectable: Map[Type, AnyRef]) {
    this.decorators = injectable
  }
  
}