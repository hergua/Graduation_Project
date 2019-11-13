package com.xmmufan.permission.service.impl

import com.xmmufan.permission.domain.rbac.UserInfo
import com.xmmufan.permission.repository.UserInfoRepository
import com.xmmufan.permission.repository.UserRepository
import com.xmmufan.permission.service.UserInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class UserInfoServiceImpl
@Autowired constructor(private val infoRepository: UserInfoRepository, private val userRepository: UserRepository) : UserInfoService {

    @Transactional(rollbackFor = [Exception::class])
    override fun saveOrUpdate(userInfo: UserInfo, userId: String) {
        userRepository.findById(userId).get().apply {
            this.info = userInfo.copy(id = this.info!!.id)
            userRepository.saveAndFlush(this)
        }

    }

    override fun queryByUser(userId: String): UserInfo? {
        return null
    }

    override fun queryById(id: String): UserInfo? {
        return infoRepository.findById(id).orElse(null)
    }
}
