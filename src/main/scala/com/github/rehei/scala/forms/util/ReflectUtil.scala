package com.github.rehei.scala.forms.util

import java.lang.reflect.ParameterizedType
import scala.reflect.runtime.universe.Type
import scala.reflect.runtime.universe.runtimeMirror
import java.lang.reflect.Method
import java.lang.reflect.Field

object ReflectUtil {

  private def getSetter(clazz: Class[_], field: String, value: Any): java.lang.reflect.Method = {
    for (method <- clazz.getMethods) {
      if (method.getName == field + "_$eq") {
        return method
      }
    }
    throw new RuntimeException("Setter not found")
  }

  def getGetterMethod(clazz: Class[_], query: String) = {
    def getSubModelMethod(method: Method, name: String) = {
      method.getReturnType.getMethod(name)
    }
    val chain = query.split("\\.")
    val methodOfFirstQuery = clazz.getMethod(chain.head)

    chain.drop(1).foldLeft(methodOfFirstQuery)(getSubModelMethod)
  }

  def getField(clazz: Class[_], query: String) = {
    def getSubModelField(field: Field, name: String) = {
      field.getType.getField(name)
    }
    val chain = query.split("\\.")
    val fieldOfFirstQuery = clazz.getField(chain.head)
    chain.drop(1).foldLeft(fieldOfFirstQuery)(getSubModelField)
  }

  def set(model: AnyRef, query: String, value: Any) {
    if (query.contains(".")) {
      val getQuery = query.substring(0, query.lastIndexOf("."))
      val getModel = get(model, getQuery)
      val setQuery = query.split("\\.").last

      val lastSetter = getSetter(getModel.getClass, setQuery, value)
      lastSetter.invoke(getModel, value.asInstanceOf[AnyRef])
    } else {
      getSetter(model.getClass, query, value).invoke(model, value.asInstanceOf[AnyRef])
    }
  }

  def get(model: AnyRef, query: String) = {
    def invokeSubModelGetter(innerModel: AnyRef, field: String) = {
      getGetterMethod(innerModel.getClass, field).invoke(innerModel)
    }
    val chain = query.split("\\.")
    chain.foldLeft(model) { invokeSubModelGetter }
  }

  def create[T](clazz: Class[T]): T = {
    clazz.newInstance().asInstanceOf[T]
  }

  def getType[T](clazz: Class[T]): Type = {
    val tmp = runtimeMirror(clazz.getClassLoader)
    tmp.classSymbol(clazz).toType
  }

  def getFirstParameterizedTypeArgument(method: java.lang.reflect.Method): Class[_] = {
    method
      .getGenericReturnType
      .asInstanceOf[ParameterizedType]
      .getActualTypeArguments()(0)
      .asInstanceOf[Class[_]]
  }

  def getFirstParameterizedTypeArgument(field: java.lang.reflect.Field) = {
    field
      .getGenericType
      .asInstanceOf[ParameterizedType]
      .getActualTypeArguments()(0)
      .asInstanceOf[Class[_]]
  }

}
