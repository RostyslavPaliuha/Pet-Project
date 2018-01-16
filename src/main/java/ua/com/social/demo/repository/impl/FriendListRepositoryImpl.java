package ua.com.social.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;
import ua.com.social.demo.repository.api.AbstractRepository;
import ua.com.social.demo.repository.api.FriendsListRepository;
import ua.com.social.demo.repository.rowMapper.FriendRowMapper;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("friendListRepository")
public class FriendListRepositoryImpl extends AbstractRepository<FriendList> implements FriendsListRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Integer create(FriendList friendList) {
        Integer[] params = new Integer[]{friendList.getProfileId(), friendList.getFriendProfileId()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO friends_list(profile_id, friend_profile_id) VALUES (?,?)", new String[]{"id"});
                    ps.setInt(1, params[0]);
                    ps.setInt(2, params[1]);
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void deleteByProfileIdFriendProfileId(Integer profileId, Integer friendProfileId) {
        jdbcOperations.update("DELETE FROM friends_list WHERE profile_id =? AND friend_profile_id=? OR profile_id =? AND friend_profile_id=?", new Object[]{profileId, friendProfileId, friendProfileId, profileId});
    }

    @Override
    public List<Friend> getFriends(Integer profileId) {
        List list = jdbcOperations.query("SELECT pd.profile_id,pd.first_name,pd.last_name FROM profile_details pd WHERE profile_id IN(SELECT fl.friend_profile_id FROM friends_list fl WHERE fl.profile_id=? )", new Object[]{profileId}, new FriendRowMapper());
        List list2 = jdbcOperations.query("SELECT pd.profile_id,pd.first_name,pd.last_name FROM profile_details pd WHERE profile_id IN(SELECT fl.profile_id FROM friends_list fl WHERE fl.friend_profile_id=?)", new Object[]{profileId}, new FriendRowMapper());
        list.addAll(list2);
        return list;
    }
}
