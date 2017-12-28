package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Photo;

public interface PhotosService {
    public boolean persist(Photo photo);

    public boolean delete(Integer id);

    public Photo get(Integer id);

    public boolean update(Photo photo);
}
