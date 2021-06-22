package org.jooq.kotlin

import org.jooq.Field
import org.jooq.InsertOnDuplicateSetMoreStep
import org.jooq.InsertOnDuplicateSetStep
import org.jooq.InsertSetMoreStep
import org.jooq.InsertSetStep
import org.jooq.Record
import org.jooq.UpdateSetMoreStep
import org.jooq.UpdateSetStep

class InsertSetStepContext<R : Record>(private val setStep: InsertSetStep<R>) {
    private var result: InsertSetMoreStep<R>? = null

    infix fun <T> Field<T>.to(value: T): InsertSetMoreStep<R> {
        result = if (result == null) {
            setStep.set(this, value)
        } else {
            result!!.set(this, value)
        }

        return result!!
    }
}

/**
 * Set the fields as part of a SQL insert statement.
 *
 * Example:
 *
 * ```
 * // Insert two records
 * dslContext
 *     .insertInto(TABLE)
 *     .values {
 *         FIELD1 to value1
 *         FIELD2 to value2
 *     }
 *     .values {
 *         FIELD1 to value3
 *         FIELD2 to value4
 *     }
 *     .execute()
 * ```
 */
fun <R : Record> InsertSetStep<R>.values(block: InsertSetStepContext<R>.() -> InsertSetMoreStep<R>): InsertSetMoreStep<R> {
    return InsertSetStepContext(this).block()
}

/**
 * Insert a new record as part of a SQL insert statement.
 *
 * Example:
 *
 * ```
 * // Insert two records
 * dslContext
 *     .insertInto(TABLE)
 *     .values {
 *         FIELD1 to value1
 *         FIELD2 to value2
 *     }
 *     .values {
 *         FIELD1 to value3
 *         FIELD2 to value4
 *     }
 *     .execute()
 * ```
 */
fun <R : Record> InsertSetMoreStep<R>.values(block: InsertSetStepContext<R>.() -> InsertSetMoreStep<R>): InsertSetMoreStep<R> {
    return InsertSetStepContext(this.newRecord()).block()
}

class UpdateSetStepContext<R : Record>(private val setStep: UpdateSetStep<R>) {
    private var result: UpdateSetMoreStep<R>? = null

    infix fun <T> Field<T>.to(value: T): UpdateSetMoreStep<R> {
        result = if (result == null) {
            setStep.set(this, value)
        } else {
            result!!.set(this, value)
        }

        return result!!
    }
}

/**
 * Set the fields as part of a SQL update.
 *
 * Example:
 *
 * ```
 * dslContext
 *     .update(TABLE)
 *     .set {
 *         FIELD1 to value1
 *         FIELD2 to value2
 *     }
 *     .where(FIELD3 eq value3)
 * ```
 */
fun <R : Record> UpdateSetStep<R>.set(block: UpdateSetStepContext<R>.() -> UpdateSetMoreStep<R>): UpdateSetMoreStep<R> {
    return UpdateSetStepContext(this).block()
}

class InsertOnDuplicateSetStepContext<R : Record>(private val insertStep: InsertOnDuplicateSetStep<R>) {
    private var result: InsertOnDuplicateSetMoreStep<R>? = null

    infix fun <T> Field<T>.to(value: T): InsertOnDuplicateSetMoreStep<R> {
        result = if (result == null) {
            insertStep.set(this, value)
        } else {
            result!!.set(this, value)
        }

        return result!!
    }
}

/**
 * Insert a new record as part of a SQL insert statement.
 *
 * Example:
 *
 * ```
 * dslContext
 *     .insertInto(TABLE)
 *     .values {
 *         FIELD1 to value1
 *         FIELD2 to value2
 *     }
 *     .onConflict()
 *     .doUpdate()
 *     .set {
 *         FIELD2 to value2
 *     }
 *     .where(FIELD1 eq value1)
 *     .execute()
 * ```
 */
fun <R : Record> InsertOnDuplicateSetStep<R>.set(block: InsertOnDuplicateSetStepContext<R>.() -> InsertOnDuplicateSetMoreStep<R>): InsertOnDuplicateSetMoreStep<R> {
    return InsertOnDuplicateSetStepContext(this).block()
}