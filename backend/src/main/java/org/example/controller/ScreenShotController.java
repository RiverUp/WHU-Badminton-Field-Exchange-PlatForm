package org.example.controller;

import org.example.entities.ScreenShot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.example.service.JsonResult;
import org.example.service.ScreenShotService;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping(path = "/screenshot")
public class ScreenShotController {
    @Autowired
    ScreenShotService screenShotService;

    @RequestMapping(path = "/order_shot", method = RequestMethod.POST)
    public JsonResult<ScreenShot> uploadOrderShot(@RequestParam("file") MultipartFile file) {
        return screenShotService.uploadOrderShot(file);
    }

    @RequestMapping(path = "/payment_shot", method = RequestMethod.POST)
    public JsonResult<ScreenShot> uploadPaymentShot(@RequestParam("file") MultipartFile file) {
        return screenShotService.uploadPaymentShot(file);
    }
    @RequestMapping(path = "/payed_shot", method = RequestMethod.POST)
    public JsonResult<ScreenShot> uploadPayedShot(@RequestParam("file") MultipartFile file) {
        return screenShotService.uploadPayedShot(file);
    }
    @RequestMapping(path = "",method = RequestMethod.GET)
    public JsonResult<ScreenShot> getScreenShot(int id){
        return screenShotService.getScreenShot(id);
    }
    @RequestMapping(path = "/evidence_picture",method = RequestMethod.POST)
    public JsonResult<ScreenShot> uploadEvidenceShot(@RequestParam("file") MultipartFile file) {
        return screenShotService.uploadEvidenceShot(file);
    }
}
