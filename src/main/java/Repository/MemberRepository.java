package Repository;


import Entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository {

    public MemberEntity userLogin(String userId);
}
