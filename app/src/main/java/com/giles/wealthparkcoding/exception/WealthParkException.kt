package com.giles.wealthparkcoding.exception

import java.io.IOException

open class WealthParkException : IOException {
    constructor() : super()
    constructor(message: String?) : super(message)
}
