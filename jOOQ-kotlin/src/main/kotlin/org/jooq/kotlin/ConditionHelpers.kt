package org.jooq.kotlin

import org.jooq.Condition
import org.jooq.impl.DSL

/**
 * Construct a condition for a value or [DSL.noCondition] if the value is null.
 *
 * Example:
 *
 * ```
 * val value: String?
 * val FIELD: Field<String>
 *
 * // If value is null, the result is DSL.noCondition
 * // If value is not null, the result is condition FIELD='<value>'
 * value.checkCondition { FIELD eq value }
 * ```
 *
 * @param block Function that produces the condition to return if [this] is not null.
 */
fun <T> T?.checkCondition(block: (T) -> Condition) =
    if (this == null) DSL.noCondition() else block(this)

/**
 * Construct a condition for a value or [DSL.noCondition] if the value is null or
 * [predicate] returns false.
 *
 * Example:
 *
 * ```
 * val value: String?
 * val FIELD: Field<String>
 *
 * // If value is null, the result is DSL.noCondition
 * // If value is empty, the result is DSL.noCondition
 * // If value is not null/empty, the result is condition FIELD='<value>'
 * value.checkCondition({ it.isNotEmpty() }, { FIELD eq value })
 * ```
 *
 * @param predicate Function that must return true to execute [block].
 * @param block Function that produces the condition to return if [this] is not null.
 */
fun <T> T?.checkCondition(predicate: (T) -> Boolean, block: (T) -> Condition) =
    if (this == null || !predicate(this)) DSL.noCondition() else block(this)

/**
 * Return [condition] if [value] is true. Otherwise, return [DSL.noCondition].
 *
 * Example:
 *
 * ```
 * val predicate: Boolean
 * val FIELD: Field<String>
 *
 * // If predicate is false, the result is DSL.noCondition
 * // If predicate is true, the result is condition FIELD='<value>'
 * value.checkCondition(condition, FIELD eq value)
 * ```
 */
fun checkCondition(value: Boolean, condition: Condition) =
    if (value) condition else DSL.noCondition()

/**
 * Return the result of [condition] if [value] is true. Otherwise, return [DSL.noCondition].
 *
 * Example:
 *
 * ```
 * val predicate: Boolean
 * val FIELD: Field<String>
 *
 * // If predicate is false, the result is DSL.noCondition
 * // If predicate is true, the result is condition FIELD='<value>'
 * value.checkCondition(condition) { FIELD eq value }
 * ```
 */
fun checkCondition(value: Boolean, condition: () -> Condition) =
    if (value) condition() else DSL.noCondition()