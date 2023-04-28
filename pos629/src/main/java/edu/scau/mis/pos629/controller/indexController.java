package edu.scau.mis.pos629.controller;

import edu.scau.mis.pos629.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
    @GetMapping("/")
    public AjaxResult index(){
        String data = "Hello SpringBoot";
        return AjaxResult.success("ok",data);
    }
}
