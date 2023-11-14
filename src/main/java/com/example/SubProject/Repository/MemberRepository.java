package com.example.SubProject.Repository;


import com.example.SubProject.Entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository {

     //jpa 쿼리 메소드 확인하고 다음 프로젝트는 spring data jpa 로 업글
     Optional<MemberEntity> userLogin(String userid);

     boolean userSingUpo(MemberEntity memberEntity);

     MemberEntity findByUserId(String userId);

}
