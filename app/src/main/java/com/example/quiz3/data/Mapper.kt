package com.example.quiz3.data

import com.example.quiz3.data.model.User
import com.example.quiz3.data.remote.model.UsersListItem

object Mapper {
    fun transformToUser(userResponse: UsersListItem): User {
        return User(
            id = userResponse._id,
            firstName = userResponse.firstName,
            lastName = userResponse.lastName,
            nationalCode = userResponse.nationalCode
        )
    }
}