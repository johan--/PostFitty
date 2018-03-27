package com.example.matthias.postfitty.Utils

import java.net.HttpURLConnection.HTTP_NOT_MODIFIED

class HttpStatus {

    constructor()

    companion object {
        /**
         * Numeric status code, 403: Forbidden
         */
        val HTTP_FORBIDDEN = 403

        /**
         * Numeric status code, 500: Internal error
         */
        val HTTP_INTERNAL_ERROR = 500

        /**
         * Numeric status code, 404: Not found
         */
        val HTTP_NOT_FOUND = 404

        /**
         * Numeric status code, 304: Not modified
         */
        val HTTP_NOT_MODIFIED = 304

        /**
         * Numeric status code, 401: Unauthorized
         */
        val HTTP_UNAUTHORIZED = 401

        /**
         * Numeric status code, 503: Unavailable
         */
        val HTTP_UNAVAILABLE = 503

        /**
         * Numeric status code, 418: I’m a teapot
         */
        val HTTP_TEAPOT = 418

        fun isSuccess(code: Int): Boolean {
            return code >= 200 && code < 300 || code == HTTP_NOT_MODIFIED
        }
    }
}