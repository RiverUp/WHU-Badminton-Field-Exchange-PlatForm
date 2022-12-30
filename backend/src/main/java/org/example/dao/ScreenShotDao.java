package org.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.entities.ScreenShot;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface ScreenShotDao {
    public ScreenShot selectScreenShotById(int id);
    public int insertScreenShot(ScreenShot screenShot);

    public void updateScreenShotUrl(@Param("screenshot_id")int screenshot_id,@Param("url")String url);
}
