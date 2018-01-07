package ua.com.social.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Friend;
import ua.com.social.demo.entity.impl.FriendList;
import ua.com.social.demo.repository.EntityRepository;
import ua.com.social.demo.repository.ExtendedEntityRepository;
import ua.com.social.demo.repository.rowMapper.AlbumRowMapper;
import ua.com.social.demo.repository.rowMapper.FriendListRowMapper;
import ua.com.social.demo.repository.rowMapper.FriendRowMapper;

import java.sql.Types;
import java.util.List;

@Repository("friendListRepository")
public class FriendListRepository implements EntityRepository<FriendList>, ExtendedEntityRepository<FriendList> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public void persist(FriendList friendList) {
        Object[] params = new Object[]{friendList.getProfileId(),friendList.getFriendProfileId()};
        int[] types = new int[]{Types.INTEGER, Types.INTEGER};
        jdbcOperations.update("INSERT INTO friends_list(profile_id, friend_profile_id) VALUES (?,?);", params, types);
    }

    @Override
    public void delete(FriendList friendList) {
        jdbcOperations.update("DELETE FROM friends_list WHERE profile_id =" + friendList.getProfileId() + " and friend_profile_id=" + friendList.getFriendProfileId() + ";");
    }

    @Override
    public FriendList get(Integer id) {
        return jdbcOperations.queryForObject("SELECT * FROM friends_list WHERE profile_id= ?", new Object[]{id}, new FriendListRowMapper());
    }

    @Override
    public Integer persistAndRetrieveId(FriendList object) {
        return null;
    }

    @Override
    public List<FriendList> getAll(Integer id) {
        List friendList = jdbcOperations.query("SELECT friend_profile_id FROM friends_list WHERE profile_id= ?", new Object[]{id}, new FriendListRowMapper());
        List friendList2 = jdbcOperations.query("SELECT profile_id FROM friends_list WHERE friend_profile_id= ?", new Object[]{id}, new FriendListRowMapper());
        friendList.addAll(friendList2);
        return friendList;
    }

    public List<Friend> getFriends(Integer id) {
        List list = jdbcOperations.query("SELECT pd.profile_id,pd.first_name,pd.last_name FROM profile_details pd WHERE profile_id IN(SELECT fl.friend_profile_id FROM friends_list fl WHERE fl.profile_id=? )", new Object[]{id}, new FriendRowMapper());
        List list2 = jdbcOperations.query("SELECT pd.profile_id,pd.first_name,pd.last_name FROM profile_details pd WHERE profile_id IN(SELECT fl.profile_id FROM friends_list fl WHERE fl.friend_profile_id=?)", new Object[]{id}, new FriendRowMapper());
        list.addAll(list2);

        return list;
    }
}
