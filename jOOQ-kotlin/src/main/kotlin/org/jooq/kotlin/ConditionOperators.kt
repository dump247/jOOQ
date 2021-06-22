package org.jooq.kotlin

import org.jooq.Condition
import org.jooq.Field
import org.jooq.PlainSQL
import org.jooq.SQL
import org.jooq.Select
import org.jooq.Support

@Support
infix fun Condition.and(other: Condition) = this.and(other)

@Support
infix fun Condition.and(other: Field<Boolean>) = this.and(other)

@Support
@PlainSQL
infix fun Condition.and(sql: SQL) = this.and(sql)

@Support
@PlainSQL
infix fun Condition.and(sql: String) = this.and(sql)

@Support
infix fun Condition.andNot(other: Condition) = this.andNot(other)

@Support
infix fun Condition.andNot(other: Field<Boolean>) = this.andNot(other)

@Support
infix fun Condition.andExists(select: Select<*>) = this.andExists(select)

@Support
infix fun Condition.andNotExists(select: Select<*>) = this.andNotExists(select)

@Support
infix fun Condition.or(other: Condition) = this.or(other)

@Support
infix fun Condition.or(other: Field<Boolean>) = this.or(other)

@Support
@PlainSQL
infix fun Condition.or(sql: SQL) = this.or(sql)

@Support
@PlainSQL
infix fun Condition.or(sql: String) = this.or(sql)

@Support
infix fun Condition.orNot(other: Condition) = this.orNot(other)

@Support
infix fun Condition.orNot(other: Field<Boolean>) = this.orNot(other)

@Support
infix fun Condition.orExists(select: Select<*>) = this.orExists(select)

@Support
infix fun Condition.orNotExists(select: Select<*>) = this.orNotExists(select)