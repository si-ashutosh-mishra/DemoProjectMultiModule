package com.example.feature_squad.business.domain.model.squad

class PlayerComparator :Comparator<PlayerItem> {

    override fun compare(playerItem1: PlayerItem?, playerItem2: PlayerItem?): Int {
//        return if(playerItem1?.isCaptain == true ) playerItem1.isCaptain else playerItem2;

        if(playerItem1?.isCaptain == true)
            return -1

        if(playerItem2?.isCaptain == true)
            return 1

        if(playerItem1?.isViceCaptain == true)
            return -1

        if(playerItem2?.isViceCaptain == true)
            return 1

        return playerItem1?.firstName?.let {
            playerItem2?.firstName ?: return@let 1
            it.compareTo(playerItem2.firstName, ignoreCase = true)
        } ?: -1

    }
}