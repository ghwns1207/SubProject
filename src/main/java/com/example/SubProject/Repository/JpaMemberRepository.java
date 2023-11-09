package com.example.SubProject.Repository;

import com.example.SubProject.Entity.MemberEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
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
}