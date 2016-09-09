package com.github.rehei.scala.forms.markup

import scala.xml.NodeSeq

class DefaultXMLMarkupFactory extends MarkupFactory[NodeSeq] {

  override def renderForm(sub: NodeSeq): NodeSeq = {
    <div>
      <h1>Form</h1>{ sub }
    </div>
  }

  override def renderSection(sub: NodeSeq): NodeSeq = {
    <div>
      <h1>Section</h1>{ sub }
    </div>
  }

  override def renderTextbox(label: String): NodeSeq = {
    <input type="text"></input>
  }

  override def renderInlineFrame(sub: NodeSeq, addButton: NodeSeq) = {
    <div>
      <div style="float: right;">{ addButton }</div>
      <div>{ sub }</div>
    </div>
  }

  override def renderInlineElement(sub: NodeSeq, removeButton: NodeSeq) = {
    <div></div>
  }

  override def renderInsertButton(addFunc: () => NodeSeq) = {
    <div>Add</div>
  }

  override def renderRemoveButton(removeFunc: () => Unit) = {
    <div>Add</div>
  }

  override def reduce(in: Iterable[NodeSeq]): NodeSeq = {
    def nodes = in.map(m => <div>{ m }</div>)
    NodeSeq.fromSeq(nodes.toSeq)
  }

}