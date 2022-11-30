package com.cxy.weberpby.controller;

import com.cxy.weberpby.model.XXZLS;
import com.cxy.weberpby.model.XXZLSERP;
import com.cxy.weberpby.model.bwBuild;
import com.cxy.weberpby.model.lbzls;
import com.cxy.weberpby.service.XXZLService;
import com.cxy.weberpby.service.autoImportService;
import com.cxy.weberpby.service.bwBuildService;
import com.cxy.weberpby.service.lbzlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/3
 * @Description 鞋型資料(傳值)
 *
 * public String countNoXXZLData()  // 檢查有多少型體沒有部位資料
 * public List<XXZLSERP> getXXZLSErp(String ARTICLE);    // 取得鞋廠BOM表
 * public List<lbzls> getBWbw(String lb);    // 取得底廠部位資料
 * public List<bwBuild> getBWData(@PathVariable String bwlb, @PathVariable String lbdh);    // 取得部位資料
 * public String insertXXZLS(@RequestBody List<XXZLS> insertXXZLS)   // Insert XXZLS
 * public String updateXXZLS(@RequestBody List<XXZLS> updateXXZLS)   // Update XXZLS
 *
 */

@RestController
public class XXZLRController {

    @Autowired
    private XXZLService xxzlService;

    @Autowired
    private bwBuildService bwBuildService;

    @Autowired
    private lbzlsService lbzlsService;

    @Autowired
    private autoImportService autoImportService;

    // 导入鞋厂型体资料(*注意、对应已放行的订单*) // return "共导入？筆型体資料"
    @GetMapping("/XXZL/updateDCXXZL/")
    public String updateDCXXZL() {
        return autoImportService.insertXXZL();
    }

    // 檢查有多少型體沒有部位資料
    @GetMapping("/XXZL/countNoXXZLData/")
    public String countNoXXZLData() {
        return xxzlService.countNoXXZLData();
    }

    // 取得鞋廠BOM表資料
    @GetMapping("/XXZL/getXXZLSErp/{ARTICLE}")
    public List<XXZLSERP> getXXZLSErp(@PathVariable String ARTICLE){
        // 取值時要考慮本身有沒有資料了、再去鞋廠找
        return xxzlService.getXXZLSErp(ARTICLE);
    }

    // 取得底廠部位資料
    @GetMapping("/XXZL/getBWbw/{lb}")
    public List<lbzls> getBWbw(@PathVariable String lb){
        return lbzlsService.get2BWbw(lb);
    }

    // 取得部位資料
    @GetMapping("/XXZL/getBWData/{bwlb}/{lbdh}")
    public List<bwBuild> getBWData(@PathVariable String bwlb, @PathVariable String lbdh){
        return bwBuildService.getAllXB(bwlb, lbdh);
    }

    // Insert XXZLS
    @PostMapping("/XXZL/insertXXZLS/")
    public String insertXXZLS(@RequestBody List<XXZLS> insertXXZLS){
        xxzlService.insertXXZLS(insertXXZLS);
        return "success";
    }

    // Update XXZLS
    @PutMapping("/XXZL/updateXXZLS/")
    public String updateXXZLS(@RequestBody List<XXZLS> updateXXZLS){
        xxzlService.updateXXZLS(updateXXZLS);
        return "success";
    }

}
