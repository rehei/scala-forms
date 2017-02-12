package com.github.rehei.scala.forms.validation.observe

import java.util.HashMap

import scala.collection.JavaConversions.asScalaSet
import scala.collection.mutable.ListBuffer

import com.github.rehei.scala.forms.validation.ValidationResult
import com.github.rehei.scala.forms.validation.Validator

class DefaultValidationObservable(protected val model: AnyRef, protected val validator: Validator)
    extends AbstractValidationObservable {

  class ValidationListenerCollection {
    val validationCallbacks = new HashMap[String, ValidationCallback]()
    val subObservables = ListBuffer[AbstractValidationObservable]()
    val extendingValidationResults = ListBuffer[ValidationResult]()
  }

  protected val listeners = new ValidationListenerCollection()

  override def appendValidationResult(validationResult: ValidationResult) {
    listeners.extendingValidationResults.append(validationResult)
  }

  override def validate() = {
    for (entry <- listeners.validationCallbacks.entrySet()) {
      entry.getValue().reset()
    }

    var isValid = true
    val map = getAggregatedValidationResultList()
    for (entry <- map.entrySet()) {
      listeners.validationCallbacks.get(entry.getKey()).validate(entry.getValue().message)
      isValid = false
    }

    for (sub <- listeners.subObservables) {
      if (!sub.validate()) {
        isValid = false
      }
    }

    isValid
  }

  protected def getAggregatedValidationResultList() = {
    val map = new HashMap[String, ValidationResult]

    aggregate(map, validator.validate(model))
    aggregate(map, listeners.extendingValidationResults)

    map
  }

  protected def aggregate(map: HashMap[String, ValidationResult], list: Seq[ValidationResult]) = {
    for (result <- list) {
      if (!result.isValid) {
        map.put(result.propertyPath, result)
      }
    }
  }

  override def appendValidationCallback(validationCallback: ValidationCallback) {
    listeners.validationCallbacks.put(validationCallback.query, validationCallback)
  }

  override def reset() = {
    for (subObservable <- listeners.subObservables) {
      subObservable.reset()
    }
    listeners.extendingValidationResults.clear()
  }

  override def appendSubValidationObservable(validationObservable: AbstractValidationObservable) = {
    listeners.subObservables.append(validationObservable)
  }

}

