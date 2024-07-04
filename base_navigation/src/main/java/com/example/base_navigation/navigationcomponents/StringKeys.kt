package com.example.base_navigation.navigationcomponents

private const val PACKAGE_KEY = "com.example.base_navigation.navigationcomponents"

internal fun viewModelStoreProviderKey(hostId: NavHostId) = "$PACKAGE_KEY:$hostId"

internal fun savedStateKey(hostId: NavHostId, entryId: NavId) = "$PACKAGE_KEY:$hostId:$entryId"