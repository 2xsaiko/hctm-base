package net.dblsaiko.hctm.common.util

import kotlin.properties.ReadOnlyProperty

inline fun <T : Any> delegatedNotNull(crossinline getter: () -> T?) = ReadOnlyProperty<Any?, T> { thisRef, property -> getter() ?: error("Can't retrieve value from ${property.name}; not initialized!") }

fun <K, R, T> Map<K, ReadOnlyProperty<R, T>>.flatten() = ReadOnlyProperty<R, Map<K, T>> { thisRef, property -> this@flatten.mapValues { (_, it) -> it.getValue(thisRef, property) } }

fun <R, T> Iterable<ReadOnlyProperty<R, T>>.flatten() = ReadOnlyProperty<R, List<T>> { thisRef, property -> this@flatten.map { it.getValue(thisRef, property) } }