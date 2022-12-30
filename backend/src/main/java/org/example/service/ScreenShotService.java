package org.example.service;

import org.example.dao.ScreenShotDao;
import org.example.entities.ScreenShot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class ScreenShotService {
    @Autowired
    ScreenShotDao screenShotDao;

    public JsonResult<ScreenShot> uploadOrderShot(@RequestParam("file") MultipartFile file) {
        JsonResult<ScreenShot> result = new JsonResult<>(null, "400", "");
        if (file.isEmpty()) {
            result.setMsg("file is empty");
            return result;
        }
        try {
            String filePath = "D:/code/javaee/LastHomework/screen_shot";
            ScreenShot screenShot = new ScreenShot();
            screenShot.setType(2);
            screenShotDao.insertScreenShot(screenShot);
            int id = screenShot.getId();
            String filename = file.getOriginalFilename();
            String suffixName = filename.substring(filename.lastIndexOf("."));
            filename = id + suffixName;
            String url=filePath + "/" + filename;
            File dest = new File(url);
            file.transferTo(dest);
            screenShotDao.updateScreenShotUrl(id,url);
            screenShot=screenShotDao.selectScreenShotById(id);
            result.setMsg("success");
            result.setCode("200");
            result.setData(screenShot);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        }finally {
            return result;
        }
    }
    public JsonResult<ScreenShot> uploadPaymentShot(@RequestParam("file") MultipartFile file) {
        JsonResult<ScreenShot> result = new JsonResult<>(null, "400", "");
        if (file.isEmpty()) {
            result.setMsg("file is empty");
            return result;
        }
        try {
            String filePath = "D:/code/javaee/LastHomework/screen_shot";
            ScreenShot screenShot = new ScreenShot();
            screenShot.setType(1);
            screenShotDao.insertScreenShot(screenShot);
            int id = screenShot.getId();
            String filename = file.getOriginalFilename();
            String suffixName = filename.substring(filename.lastIndexOf("."));
            filename = id + suffixName;
            String url = filePath + "/" + filename;
            File dest = new File(url);
            file.transferTo(dest);
            screenShotDao.updateScreenShotUrl(id, url);
            screenShot=screenShotDao.selectScreenShotById(id);
            result.setMsg("success");
            result.setCode("200");
            result.setData(screenShot);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }
    public JsonResult<ScreenShot> uploadPayedShot(@RequestParam("file") MultipartFile file) {
        JsonResult<ScreenShot> result = new JsonResult<>(null, "400", "");
        if (file.isEmpty()) {
            result.setMsg("file is empty");
            return result;
        }
        try {
            String filePath = "D:/code/javaee/LastHomework/screen_shot";
            ScreenShot screenShot = new ScreenShot();
            screenShot.setType(0);
            screenShotDao.insertScreenShot(screenShot);
            int id = screenShot.getId();
            String filename = file.getOriginalFilename();
            String suffixName = filename.substring(filename.lastIndexOf("."));
            filename = id + suffixName;
            String url = filePath + "/" + filename;
            File dest = new File(url);
            file.transferTo(dest);
            screenShotDao.updateScreenShotUrl(id, url);
            screenShot=screenShotDao.selectScreenShotById(id);
            result.setMsg("success");
            result.setCode("200");
            result.setData(screenShot);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }
    public JsonResult<ScreenShot> uploadEvidenceShot(@RequestParam("file") MultipartFile file) {
        JsonResult<ScreenShot> result = new JsonResult<>(null, "400", "");
        if (file.isEmpty()) {
            result.setMsg("file is empty");
            return result;
        }
        try {
            String filePath = "D:/code/javaee/LastHomework/screen_shot";
            ScreenShot screenShot = new ScreenShot();
            screenShot.setType(3);
            screenShotDao.insertScreenShot(screenShot);
            int id = screenShot.getId();
            String filename = file.getOriginalFilename();
            String suffixName = filename.substring(filename.lastIndexOf("."));
            filename = id + suffixName;
            String url = filePath + "/" + filename;
            File dest = new File(url);
            file.transferTo(dest);
            screenShotDao.updateScreenShotUrl(id, url);
            screenShot=screenShotDao.selectScreenShotById(id);
            result.setMsg("success");
            result.setCode("200");
            result.setData(screenShot);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }
    public JsonResult<ScreenShot> getScreenShot(int id){
        JsonResult<ScreenShot> result = new JsonResult<>(null, "400", "");
        try {
            ScreenShot screenShot=screenShotDao.selectScreenShotById(id);
            result.setMsg("success");
            result.setCode("200");
            result.setData(screenShot);
        } catch (Exception exception) {
            System.out.println(exception);
            result.setMsg(exception.toString());
        } finally {
            return result;
        }
    }
}
