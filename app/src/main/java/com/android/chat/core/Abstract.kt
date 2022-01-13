package com.android.chat.core

abstract class Abstract {

    interface Object<T, M : Mapper> {

        fun map(mapper: M): T
    }

    interface UiObject {
        class Empty : UiObject
    }

    interface Mapper {

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }

        interface DataToDomain<S, R> : Data<S, R> {
            fun map(error: Exception): R
        }

        interface DomainToUi<S, T> : Data<S, T> {
            fun map(error: String): T
        }

        class Empty : Mapper
    }
}