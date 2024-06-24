package com.example.demoprojectmultimodule.util

enum class AppType(val id: Int) {
    KKR(1106),
    LAKR(1107),
    TKR(1108),
    ADKR(1109);

    companion object {
        fun getAppType(value: Int) =
            if (value > 0) values().first { it.id == value } else values().first()

        fun getDefaultAppType() = values().first().id

        fun getNationalityIdOfTeam(value: Int): String {
            return when (value) {
                KKR.id -> Constants.NATIONALITY_ID_INDIA
                LAKR.id -> Constants.NATIONALITY_ID_USA
                TKR.id -> Constants.NATIONALITY_ID_WI
                ADKR.id -> Constants.NATIONALITY_ID_INDIA
                else ->
                    ""

            }
        }
        fun getClubName(value: Int): String {
            return when (value) {
                KKR.id -> Constants.KKR
                LAKR.id -> Constants.LAKR
                TKR.id -> Constants.TKR
                ADKR.id -> Constants.ADKR
                else ->
                    ""

            }
        }
        fun getAppSubType(value: Int): String {
            return when (value) {
                KKR.id -> "1"
                LAKR.id -> "2"
                TKR.id -> "3"
                ADKR.id -> "4"
                else -> ""
            }
        }

    }
}

enum class ClubName(val value: Int){

}