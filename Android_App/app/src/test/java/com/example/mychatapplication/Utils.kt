package com.example.mychatapplication

import org.mockito.Mockito.any

class Utils {
    companion object {
        fun <T> anyValue(): T {
            return any()
        }
    }
}
