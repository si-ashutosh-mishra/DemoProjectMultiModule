package com.example.base_navigation.navigationcomponents

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

/**
 * Parcelable representation of [ScopedNavHostEntry] data.
 */
@Parcelize
internal class ScopedNavHostEntryRecord<out S>(
    val id: NavId,
    val scope: @RawValue S
) : Parcelable

internal fun <S> ScopedNavHostEntry<S>.toScopedHostEntryRecord() = ScopedNavHostEntryRecord(
    id = id,
    scope = scope
)