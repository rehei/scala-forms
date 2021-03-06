package com.github.rehei.scala.forms.binding

import scala.collection.JavaConversions._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

import com.github.rehei.scala.forms.BindableComponent
import com.github.rehei.scala.forms.Form
import com.github.rehei.scala.forms.Renderable
import com.github.rehei.scala.forms.markup.AbstractMarkupFactory
import com.github.rehei.scala.forms.util.ReflectUtil
import com.github.rehei.scala.forms.markup.AbstractInlineMarkupFactory
import com.github.rehei.scala.forms.decorators.MaxDecorator
import com.github.rehei.scala.forms.validation.observe.AbstractValidationObservable
import com.github.rehei.scala.forms.validation.Validator
import com.github.rehei.scala.forms.markup.WrappedMarkupFactory

class InlineBinding(val form: Form) extends AbstractBinding {

  override def bind[T](validationObservable: AbstractValidationObservable, context: BindableComponent, model: AnyRef, markupFactory: AbstractMarkupFactory[T]) = {
    val collection = context.getter(model).asInstanceOf[java.util.Collection[AnyRef]]
    val inlineMarkupFactory = markupFactory.createInlineMarkupFactory(validationObservable, this)

    def insertFunc = {
      val value = ReflectUtil.create(getSubModelClazz(context)).asInstanceOf[AnyRef]
      collection.add(value)
      renderInlineElement(validationObservable, markupFactory, inlineMarkupFactory, value, () => removeFunc(value))
    }

    def removeFunc(value: AnyRef) = {
      collection.remove(value)
    }

    renderInlineFrame(validationObservable, markupFactory, inlineMarkupFactory, collection, insertFunc _, removeFunc _)
  }

  def renderInlineFrame[T](validationObservable: AbstractValidationObservable, markupFactory: AbstractMarkupFactory[T], inlineMarkupFactory: AbstractInlineMarkupFactory[T], collection: java.util.Collection[AnyRef], addFunc: () => T, removeFunc: (AnyRef) => Unit) = {

    inlineMarkupFactory.renderInlineFrame(
      markupFactory.reduce(
        collection.map(
          value => renderInlineElement(validationObservable, markupFactory, inlineMarkupFactory, value, () => removeFunc(value)))),
      addFunc)
  }

  def renderInlineElement[T](validationObservable: AbstractValidationObservable, markupFactory: AbstractMarkupFactory[T], inlineMarkupFactory: AbstractInlineMarkupFactory[T], value: AnyRef, removeFunc: () => Unit) = {

    val wrappedMarkupFactory = new WrappedMarkupFactory(markupFactory) {
      override def createValidationObservable(validator: Validator,model: AnyRef) = {
        val tmp = base.createValidationObservable(validator, model)
        validationObservable.appendSubValidationObservable(tmp)
        tmp
      }
    }

    val inlineFormXml = form.nested(true).render(value, wrappedMarkupFactory)
    inlineMarkupFactory.renderInlineElement(inlineFormXml, removeFunc)
  }

  def getSubModelClazz(context: BindableComponent) = {
    context.getFirstParameterizedTypeArgument()
  }

  def max(max: Int) = {
    this.decorateWith(MaxDecorator(max))
  }

  def max() = {
    this.getDecorator[MaxDecorator].map(_.max)
  }

}