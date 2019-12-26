package com.edu.springboot.cloud.zuul;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;



/**
 * @description:
 * @program: eureka-service
 * @author: dingkaige
 * @date: 2019-12-23 11:05
 **/
@RestController
@RequestMapping("api")
@Log4j2
public class Controller {

    @GetMapping("findById")
    public boolean findById(@RequestParam(name = "id") Long id) {
        log.info("Zuul-->findById:"+id);
        return true;
    }
}
