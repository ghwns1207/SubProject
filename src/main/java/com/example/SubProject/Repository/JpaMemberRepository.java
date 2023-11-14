package com.example.SubProject.Repository;

import com.example.SubProject.Entity.MemberEntity;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
@Slf4j
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em){this.em = em;}

    @Override
    public Optional<MemberEntity> userLogin(String userid) {
        MemberEntity member =  em.createQuery("select m from MemberEntity  m where m.userid = :userid and m.useyn = 1",MemberEntity.class)
                .setParameter("userid", userid)
                .getSingleResult();
        return Optional.ofNullable(member);
    }

    @Override
    public boolean userSingUpo(MemberEntity memberEntity) {
       try {
           em.persist(memberEntity);
           return true;
       }catch (Exception e){
           log.info("e ={}",e);
           return false;
       }
    }

    @Override
    public MemberEntity findByUserId(String userid) {
        MemberEntity member =  em.createQuery("select m from MemberEntity  m where m.userid = :userid and m.useyn = 1",MemberEntity.class)
                .setParameter("userid", userid)
                .getSingleResult();
        if(member != null){
            log.info("member={}", member);
        }
        return member;
    }


}
