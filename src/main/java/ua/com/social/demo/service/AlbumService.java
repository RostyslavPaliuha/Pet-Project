package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Album;


public interface AlbumService {
    public boolean persist(Album album);

    public boolean delete(Integer id);

    public Album get(Integer id);

    public boolean update(Album album);
}
