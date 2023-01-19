package com.imoviedb.app.presentation.ui.utils

import com.imoviedb.app.presentation.ui.utils.ErrorCodes.CODE_401
import com.imoviedb.app.presentation.ui.utils.ErrorCodes.CODE_403
import com.imoviedb.app.presentation.ui.utils.ErrorCodes.CODE_500

class ErrorCodeMapper {

    companion object {
        //mock function to populate error screen with static message
        fun getMessageDescriptionFromCode(code: Int): Pair<String, String> {
            return when (code) {
                CODE_401 -> {
                    "Error while processing" to "Incorrect Request"
                }
                CODE_500 -> {
                    "Error while processing" to "Wrong gatway"
                }
                CODE_403 -> {
                    "Error while processing" to "Wrong Parameters"
                }
                else -> {
                    "Error while processing" to "Generic Error"
                }
            }
        }
    }
}

interface Code

//Util class to pass error result code for error handling
object ErrorCodes : Code {
    const val CODE_401: Int = 401
    const val CODE_500: Int = 500
    const val CODE_403: Int = 403
    const val INTERNAL: Int = -1
}