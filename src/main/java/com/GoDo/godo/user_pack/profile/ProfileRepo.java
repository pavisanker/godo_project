package com.GoDo.godo.user_pack.profile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<ProfileModel,String> {
    boolean existsByPhoneNumber(String phoneNumber);
}
