package com.tba.todox.feature.home.add_task.usecase

import androidx.annotation.ReturnThis
import com.tba.todox.feature.home.add_task.AddTaskState
import com.tba.todox.feature.home.add_task.usecase.ValidateTask.ValidationField
import java.time.LocalDate
import java.time.LocalTime

// TODO: melhorar essa validação. Não está muito boa.

sealed class ValidationResult(
    isValid: Boolean,
    val errors: Map<ValidationField, List<String>>?,
) {
    data object Success : ValidationResult(true, null)
    data class Error(val e: Map<ValidationField, List<String>>) : ValidationResult(false, e)

    companion object {
        fun from(vararg validationResults: ValidationResult): ValidationResult {
            val combinedErrors = mutableMapOf<ValidationField, List<String>>()

            validationResults.forEach { result ->
                if (result is Error) {
                    combinedErrors.combineWith(result.e)
                }
            }

            return if (combinedErrors.isNotEmpty()) {
                Error(combinedErrors)
            } else {
                Success
            }
        }

        fun from(errors: Map<ValidationField, List<String>>?): ValidationResult {
            return errors
                ?.takeIf { e -> e.isNotEmpty() }
                ?.takeIf { e -> e.all { l -> l.value.isNotEmpty() } }
                ?.let { m -> Error(m) }
                ?: Success
        }

        fun from(key: ValidationField, errors: List<String>?): ValidationResult {
            return errors
                ?.takeIf { e -> e.isNotEmpty() }
                ?.let { m -> Error(mapOf(Pair(key, m))) }
                ?: Success
        }
    }
}


//enum class ValidationField {
//    TITLE, DESCRIPTION, DATE, TIME, PRIORITY
//}

typealias ValidationError = Pair<ValidationField, List<String>>

// TODO: criar uma lista de erros em vez de strings; mudar esse indice ser um enum ValidationField
class ValidateTask {
    enum class ValidationField {
        TITLE, DESCRIPTION, DATE, TIME, PRIORITY
    }

    companion object {
        fun validate(task: AddTaskState): ValidationResult {
            val titleValidation = validateTitle(task.title)
            val timeValidation = validateTime(task.time)
            val dateValidation = validateDate(task.date)

            return ValidationResult.from(titleValidation, timeValidation, dateValidation)
        }
    }
}

//

fun validateTitle(title: String): ValidationResult {
    val key = ValidationField.TITLE
    val errors = mutableListOf<String>()

    if (title.isEmpty() || title.isBlank()) {
        errors.add("blank or null")
    }

    return ValidationResult.from(key, errors)
}

fun validateTime(time: LocalTime?): ValidationResult {
    val key = ValidationField.TIME
    val errors = mutableListOf<String>()

    if (time == null) {
        errors.add("time cannot be NULL")
        return ValidationResult.Error(mapOf(Pair(key, errors)))
    }

    if (time.hour > 3) {
        errors.add("hour cannot be greater than 3")
    }

    return ValidationResult.from(key, errors)
}

fun validateDate(date: LocalDate?): ValidationResult {
    val key = ValidationField.DATE
    val errors = mutableListOf<String>()

    if (date == null) {
        errors.add("date cannot be NULL")
    }

    return ValidationResult.from(key, errors)
}

private fun MutableMap<ValidationField, List<String>>.combineWith(otherMap: Map<ValidationField, List<String>>) {
    otherMap.forEach { (key, value) ->
        this.merge(key, value) { oldList, newList ->
            oldList + newList
        }
    }
}



private fun <F, E> MutableMap<F, List<E>>.appendElement(field: F, element: E) {
    this[field] = this.getOrDefault(
        field,
        mutableListOf()
    ) + mutableListOf(element)
}
