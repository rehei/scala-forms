package com.github.rehei.scala.forms.markup

import scala.xml.NodeSeq
import com.github.rehei.scala.forms.SubRendering

import com.github.rehei.scala.forms.markup

class DefaultXMLMarkupFactory extends MarkupFactory[NodeSeq] {

  def renderForm(subRendering: SubRendering[NodeSeq]): NodeSeq = {
    <div>
      <h1>Form</h1>
      { subRendering.render() }
    </div>
  }

  def renderInline(elements: Seq[NodeSeq]): NodeSeq = {
    <div>
      <h1>Inline</h1>
      { elements }
    </div>
  }

  def renderSection(subRendering: SubRendering[NodeSeq]): NodeSeq = {
    <div>
      <h1>Section</h1>
      { subRendering.render() }
    </div>
  }

  def renderTextbox(): NodeSeq = {
    <input type="text"></input>
  }

}