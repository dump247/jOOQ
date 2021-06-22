package org.jooq.kotlin

import org.jooq.Field
import org.jooq.QuantifiedSelect
import org.jooq.Record1
import org.jooq.SQLDialect.CUBRID
import org.jooq.SQLDialect.DERBY
import org.jooq.SQLDialect.FIREBIRD
import org.jooq.SQLDialect.H2
import org.jooq.SQLDialect.HSQLDB
import org.jooq.SQLDialect.IGNITE
import org.jooq.SQLDialect.MARIADB
import org.jooq.SQLDialect.MYSQL
import org.jooq.SQLDialect.POSTGRES
import org.jooq.SQLDialect.SQLITE
import org.jooq.Select
import org.jooq.SortOrder
import org.jooq.Support
import org.jooq.Result as JooqResult

// ------------------------------------------------------------------------
// Conversion of field into a sort field
// ------------------------------------------------------------------------

@Support
infix fun <T> Field<T>.sort(order: SortOrder) = this.sort(order)

@Support
infix fun <T> Field<T>.sortAsc(sortList: Collection<T>) = this.sortAsc(sortList)

@Support
infix fun <T> Field<T>.sortAsc(sortList: Array<T>) = this.sortAsc(*sortList)

@Support
infix fun <T> Field<T>.sortDesc(sortList: Collection<T>) = this.sortDesc(sortList)

@Support
infix fun <T> Field<T>.sortDesc(sortList: Array<T>) = this.sortDesc(*sortList)

@Support
infix fun <T, Z> Field<T>.sort(sortList: Map<T, Z>) = this.sort(sortList)

// ------------------------------------------------------------------------
// Arithmetic operations
// ------------------------------------------------------------------------

@Support
infix fun <T> Field<T>.pow(value: Number) = this.pow(value)

@Support
infix fun <T> Field<T>.pow(value: Field<out Number>) = this.pow(value)

@Support
infix fun <T> Field<T>.power(value: Number) = this.power(value)

@Support
infix fun <T> Field<T>.power(value: Field<out Number>) = this.power(value)

// ------------------------------------------------------------------------
// Bitwise operations
// ------------------------------------------------------------------------

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitAnd(value: T) = this.bitAnd(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitAnd(value: Field<T>) = this.bitAnd(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitNand(value: T) = this.bitNand(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitNand(value: Field<T>) = this.bitNand(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitOr(value: T) = this.bitOr(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitOr(value: Field<T>) = this.bitOr(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitNor(value: T) = this.bitNor(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitNor(value: Field<T>) = this.bitNor(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitXor(value: T) = this.bitXor(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitXor(value: Field<T>) = this.bitXor(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitXNor(value: T) = this.bitXNor(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.bitXNor(value: Field<T>) = this.bitXNor(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.shl(value: Number) = this.shl(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.shl(value: Field<out Number>) = this.shl(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.shr(value: Number) = this.shr(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.shr(value: Field<out Number>) = this.shr(value)

// ------------------------------------------------------------------------
// NULL predicates
// ------------------------------------------------------------------------

enum class Is {
    NULL
}

@Support
infix fun <T> Field<T>.`is`(predicate: Is) = when (predicate) {
    Is.NULL -> this.isNull
}

@Support
infix fun <T> Field<T>.isNot(predicate: Is) = when (predicate) {
    Is.NULL -> this.isNotNull
}

// ------------------------------------------------------------------------
// DISTINCT predicates
// ------------------------------------------------------------------------

@Support
infix fun <T> Field<T>.isDistinctFrom(value: T) = this.isDistinctFrom(value)

@Support
infix fun <T> Field<T>.isDistinctFrom(value: Field<T>) = this.isDistinctFrom(value)

@Support
infix fun <T> Field<T>.isDistinctFrom(select: Select<out Record1<T>>) = this.isDistinctFrom(select)

@Support
infix fun <T> Field<T>.isNotDistinctFrom(value: T) = this.isNotDistinctFrom(value)

@Support
infix fun <T> Field<T>.isNotDistinctFrom(value: Field<T>) = this.isNotDistinctFrom(value)

@Support
infix fun <T> Field<T>.isNotDistinctFrom(select: Select<out Record1<T>>) =
    this.isNotDistinctFrom(select)

// ------------------------------------------------------------------------
// LIKE_REGEX predicates
// ------------------------------------------------------------------------

@Support(CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.likeRegex(pattern: String) = this.likeRegex(pattern)

@Support(CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.likeRegex(pattern: Field<String>) = this.likeRegex(pattern)

@Support(CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.notLikeRegex(pattern: String) = this.notLikeRegex(pattern)

@Support(CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.notLikeRegex(pattern: Field<String>) = this.notLikeRegex(pattern)

// ------------------------------------------------------------------------
// SIMILAR TO predicates
// ------------------------------------------------------------------------

@Support(FIREBIRD, POSTGRES)
infix fun <T> Field<T>.similarTo(value: String) = this.similarTo(value)

@Support(FIREBIRD, POSTGRES)
infix fun <T> Field<T>.similarTo(value: Field<String>) = this.similarTo(value)

@Support(FIREBIRD, POSTGRES)
infix fun <T> Field<T>.notSimilarTo(value: String) = this.notSimilarTo(value)

@Support(FIREBIRD, POSTGRES)
infix fun <T> Field<T>.notSimilarTo(value: Field<String>) = this.notSimilarTo(value)

// ------------------------------------------------------------------------
// LIKE predicates
// ------------------------------------------------------------------------

@Support
infix fun <T> Field<T>.like(value: String) = this.like(value)

@Support
infix fun <T> Field<T>.like(value: Field<String>) = this.like(value)

@Support
infix fun <T> Field<T>.like(value: QuantifiedSelect<Record1<String>>) = this.like(value)

@Support
infix fun <T> Field<T>.likeIgnoreCase(value: String) = this.likeIgnoreCase(value)

@Support
infix fun <T> Field<T>.likeIgnoreCase(value: Field<String>) = this.likeIgnoreCase(value)

@Support
infix fun <T> Field<T>.notLike(value: String) = this.notLike(value)

@Support
infix fun <T> Field<T>.notLike(value: Field<String>) = this.notLike(value)

@Support
infix fun <T> Field<T>.notLike(value: QuantifiedSelect<Record1<String>>) = this.notLike(value)

@Support
infix fun <T> Field<T>.notLikeIgnoreCase(value: String) = this.notLikeIgnoreCase(value)

@Support
infix fun <T> Field<T>.notLikeIgnoreCase(value: Field<String>) = this.notLikeIgnoreCase(value)

@Support
infix fun <T> Field<T>.contains(value: T) = this.contains(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.contains(value: Field<T>) = this.contains(value)

@Support
infix fun <T> Field<T>.notContains(value: T) = this.notContains(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.notContains(value: Field<T>) = this.notContains(value)

@Support
infix fun <T> Field<T>.containsIgnoreCase(value: T) = this.containsIgnoreCase(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.containsIgnoreCase(value: Field<T>) = this.containsIgnoreCase(value)

@Support
infix fun <T> Field<T>.notContainsIgnoreCase(value: T) = this.notContainsIgnoreCase(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.notContainsIgnoreCase(value: Field<T>) = this.notContainsIgnoreCase(value)

@Support
infix fun <T> Field<T>.startsWith(value: T) = this.startsWith(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.startsWith(value: Field<T>) = this.startsWith(value)

@Support
infix fun <T> Field<T>.startsWithIgnoreCase(value: T) = this.startsWithIgnoreCase(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.startsWithIgnoreCase(value: Field<T>) = this.startsWithIgnoreCase(value)

@Support
infix fun <T> Field<T>.endsWith(value: T) = this.endsWith(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.endsWith(value: Field<T>) = this.endsWith(value)

@Support
infix fun <T> Field<T>.endsWithIgnoreCase(value: T) = this.endsWithIgnoreCase(value)

@Support(CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE)
infix fun <T> Field<T>.endsWithIgnoreCase(value: Field<T>) = this.endsWithIgnoreCase(value)

// ------------------------------------------------------------------------
// IN predicates
// ------------------------------------------------------------------------

@Support
infix fun <T> Field<T>.`in`(values: Collection<*>) = this.`in`(values)

@Support
infix fun Field<Long>.`in`(values: LongProgression) = when (values.step) {
    1L -> this.between(values.first, values.last)
    -1L -> this.between(values.last, values.first)
    else -> this.`in`(values.toList())
}

@Support
infix fun Field<Int>.`in`(values: IntProgression) = when (values.step) {
    1 -> this.between(values.first, values.last)
    -1 -> this.between(values.last, values.first)
    else -> this.`in`(values.toList())
}

@Support
infix fun Field<Char>.`in`(values: CharProgression) = when (values.step) {
    1 -> this.between(values.first, values.last)
    -1 -> this.between(values.last, values.first)
    else -> this.`in`(values.toList())
}

@Support
infix fun <T> Field<T>.`in`(result: JooqResult<out Record1<T>>) = this.`in`(result)

@Support
infix fun <T> Field<T>.`in`(values: Array<T>) = this.`in`(*values)

@Support
infix fun <T> Field<T>.`in`(values: Array<Field<*>>) = this.`in`(*values)

@Support
infix fun <T> Field<T>.`in`(query: Select<out Record1<T>>) = this.`in`(query)

@Support
infix fun <T> Field<T>.notIn(values: Collection<*>) = this.notIn(values)

@Support
infix fun <T> Field<T>.notIn(result: JooqResult<out Record1<T>>) = this.notIn(result)

@Support
infix fun <T> Field<T>.notIn(values: Array<T>) = this.notIn(*values)

@Support
infix fun <T> Field<T>.notIn(values: Array<Field<*>>) = this.notIn(*values)

@Support
infix fun <T> Field<T>.notIn(query: Select<out Record1<T>>) = this.notIn(query)

// ------------------------------------------------------------------------
// BETWEEN predicates
// ------------------------------------------------------------------------

@Support
infix fun <T : Comparable<T>> Field<T>.between(range: ClosedRange<T>) =
    this.between(range.start, range.endInclusive)

@Support
infix fun <T : Comparable<T>> Field<T>.betweenSymmetric(range: ClosedRange<T>) =
    this.betweenSymmetric(range.start, range.endInclusive)

@Support
infix fun <T : Comparable<T>> Field<T>.notBetween(range: ClosedRange<T>) =
    this.notBetween(range.start, range.endInclusive)

@Support
infix fun <T : Comparable<T>> Field<T>.notBetweenSymmetric(range: ClosedRange<T>) =
    this.notBetweenSymmetric(range.start, range.endInclusive)

// ------------------------------------------------------------------------
// Comparison predicates
// ------------------------------------------------------------------------

@Support
infix fun <T> Field<T>.equal(value: T) = this.equal(value)

@Support
infix fun <T> Field<T>.equal(value: Field<T>) = this.equal(value)

@Support
infix fun <T> Field<T>.equal(query: Select<out Record1<T>>) = this.equal(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.equal(query: QuantifiedSelect<out Record1<T>>) = this.equal(query)

@Support
infix fun <T> Field<T>.eq(value: T) = this.eq(value)

@Support
infix fun <T> Field<T>.eq(value: Field<T>) = this.eq(value)

@Support
infix fun <T> Field<T>.eq(query: Select<out Record1<T>>) = this.eq(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.eq(query: QuantifiedSelect<out Record1<T>>) = this.eq(query)

@Support
infix fun <T> Field<T>.notEqual(value: T) = this.notEqual(value)

@Support
infix fun <T> Field<T>.notEqual(value: Field<T>) = this.notEqual(value)

@Support
infix fun <T> Field<T>.notEqual(query: Select<out Record1<T>>) = this.notEqual(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.notEqual(query: QuantifiedSelect<out Record1<T>>) = this.notEqual(query)

@Support
infix fun <T> Field<T>.ne(value: T) = this.ne(value)

@Support
infix fun <T> Field<T>.ne(value: Field<T>) = this.ne(value)

@Support
infix fun <T> Field<T>.ne(query: Select<out Record1<T>>) = this.ne(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.ne(query: QuantifiedSelect<out Record1<T>>) = this.ne(query)

@Support
infix fun <T> Field<T>.lessThan(value: T) = this.lessThan(value)

@Support
infix fun <T> Field<T>.lessThan(value: Field<T>) = this.lessThan(value)

@Support
infix fun <T> Field<T>.lessThan(query: Select<out Record1<T>>) = this.lessThan(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.lessThan(query: QuantifiedSelect<out Record1<T>>) = this.lessThan(query)

@Support
infix fun <T> Field<T>.lt(value: T) = this.lt(value)

@Support
infix fun <T> Field<T>.lt(value: Field<T>) = this.lt(value)

@Support
infix fun <T> Field<T>.lt(query: Select<out Record1<T>>) = this.lt(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.lt(query: QuantifiedSelect<out Record1<T>>) = this.lt(query)

@Support
infix fun <T> Field<T>.lessOrEqual(value: T) = this.lessOrEqual(value)

@Support
infix fun <T> Field<T>.lessOrEqual(value: Field<T>) = this.lessOrEqual(value)

@Support
infix fun <T> Field<T>.lessOrEqual(query: Select<out Record1<T>>) = this.lessOrEqual(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.lessOrEqual(query: QuantifiedSelect<out Record1<T>>) =
    this.lessOrEqual(query)

@Support
infix fun <T> Field<T>.le(value: T) = this.le(value)

@Support
infix fun <T> Field<T>.le(value: Field<T>) = this.le(value)

@Support
infix fun <T> Field<T>.le(query: Select<out Record1<T>>) = this.le(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.le(query: QuantifiedSelect<out Record1<T>>) = this.le(query)

@Support
infix fun <T> Field<T>.greaterThan(value: T) = this.greaterThan(value)

@Support
infix fun <T> Field<T>.greaterThan(value: Field<T>) = this.greaterThan(value)

@Support
infix fun <T> Field<T>.greaterThan(query: Select<out Record1<T>>) = this.greaterThan(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.greaterThan(query: QuantifiedSelect<out Record1<T>>) =
    this.greaterThan(query)

@Support
infix fun <T> Field<T>.gt(value: T) = this.gt(value)

@Support
infix fun <T> Field<T>.gt(value: Field<T>) = this.gt(value)

@Support
infix fun <T> Field<T>.gt(query: Select<out Record1<T>>) = this.gt(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.gt(query: QuantifiedSelect<out Record1<T>>) = this.gt(query)

@Support
infix fun <T> Field<T>.greaterOrEqual(value: T) = this.greaterOrEqual(value)

@Support
infix fun <T> Field<T>.greaterOrEqual(value: Field<T>) = this.greaterOrEqual(value)

@Support
infix fun <T> Field<T>.greaterOrEqual(query: Select<out Record1<T>>) = this.greaterOrEqual(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.greaterOrEqual(query: QuantifiedSelect<out Record1<T>>) =
    this.greaterOrEqual(query)

@Support
infix fun <T> Field<T>.ge(value: T) = this.ge(value)

@Support
infix fun <T> Field<T>.ge(value: Field<T>) = this.ge(value)

@Support
infix fun <T> Field<T>.ge(query: Select<out Record1<T>>) = this.ge(query)

@Support(CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES)
infix fun <T> Field<T>.ge(query: QuantifiedSelect<out Record1<T>>) = this.ge(query)

@Support
infix fun Field<String>.equalIgnoreCase(value: String) = this.equalIgnoreCase(value)

@Support
infix fun Field<String>.equalIgnoreCase(value: Field<String>) = this.equalIgnoreCase(value)

@Support
infix fun Field<String>.notEqualIgnoreCase(value: String) = this.notEqualIgnoreCase(value)

@Support
infix fun Field<String>.notEqualIgnoreCase(value: Field<String>) = this.notEqualIgnoreCase(value)
