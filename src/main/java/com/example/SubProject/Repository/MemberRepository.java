package com.example.SubProject.Repository;


import com.example.SubProject.Entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository {

     Optional<MemberEntity> userLogin(String userId);
}
