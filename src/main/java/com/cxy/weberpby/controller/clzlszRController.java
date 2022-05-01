package com.cxy.weberpby.controller;

import com.cxy.weberpby.dto.CLZLUpdateParams;
import com.cxy.weberpby.model.clzlsl;
import com.cxy.weberpby.service.CLZLService;
import com.cxy.weberpby.service.clzlslService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/4/29
 * @Description 標準配方資料(傳值)
 *
 * public List<clzlsl> getclzlszList(@PathVariable String cldh) // 標準配方組成明細
 * public String updateCLZL(@RequestBody CLZLUpdateParams cup)  // Update CLZL & clzlsz
 * public String insertCLZL(@RequestBody CLZLUpdateParams cup)  // Insert CLZL & clzlsz
 * public String deleteCLZL(@RequestBody String lb) // Delete CLZL & clzlsz
 */

@RestController
public class clzlszRController {

    @Autowired
    private CLZLService clzlService;
    @Autowired
    private clzlslService clzlslService;

    // 標準配方組成明細
    @GetMapping("/clzlszList/{cldh}")
    public List<clzlsl> getclzlszList(@PathVariable String cldh) {
        return clzlslService.getclzlsz(cldh);
    }

    // Update CLZL & clzlsz
    @PutMapping("/clzlsz/updateCLZL/")
    public String updateCLZL(@RequestBody CLZLUpdateParams cup) {
        clzlService.updateCLZL("R", cup);
        return "success";
    }

    // Insert CLZL & clzlsz
    @PostMapping("/clzlsz/insertCLZL/")
    public String insertCLZL(@RequestBody CLZLUpdateParams cup) {
        clzlService.insertCLZL("R", cup);
        return "success";
    }

    // Delete CLZL & clzlsz
    @DeleteMapping("/clzlsz/deleteCLZL/")
    public String deleteCLZL(@RequestBody String cldh) {
        clzlService.deleteCLZL(cldh);
        clzlslService.deleteclzlsz(cldh);
        return "success";
    }
}
