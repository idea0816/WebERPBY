package com.cxy.weberpby.controller;

import com.cxy.weberpby.model.bwBuild;
import com.cxy.weberpby.model.bwBuild2;
import com.cxy.weberpby.service.bwBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/5/6
 * @Description 部位规格建立(模具、部位、Size對照)(傳值)
 *
 * 修改點：
 * 配方要最新版次、且顯示名稱、不要代號
 *
 * List<bwBuild> getBMC(String NO); // 部位B模具M顏色C
 * List<bwBuild2> getP(String NO); // 配方P
 * List<bwBuild2> getSG(String NO, String cldh, String YSSM); // Size,Gram
 * String getVersion(String NO);    // 標準部位項次號
 * public String insertbwBuild(@RequestBody List<bwBuild> insertBwBuild)    // Insert XXZLS1_B
 * public String updatebwBuild(@RequestBody List<bwBuild> insertBwBuild)    // Update XXZLS1_B
 * public String deleteXXZLS1_BB(@PathVariable String delNO)    // Delete XXZLS1_B
 * public String insertXXZLS1_BB(@RequestBody List<bwBuild2> insertXXZLS1_BB)   // Insert XXZLS1_BB
 * public String deleteXXZLS1_BB(@RequestBody List<bwBuild2> deleteXXZLS1_BB)   // Delete XXZLS1_BB
 *
 */

@RestController
public class bwBuildRController {

    @Autowired
    private bwBuildService bwBuildService;

    // 部位B模具M顏色C對照
    @GetMapping("/bwBuild/getList/{NO}")
    public List<bwBuild> getBMC(@PathVariable String NO) {
        return bwBuildService.getBMC(NO+"*%");
    }

    // 配方P
    @GetMapping("/bwBuild/getP/{NO}")
    public List<bwBuild2> getP(@PathVariable String NO){
        return bwBuildService.getP(NO);
    }

    // Size,Gram
    @GetMapping("/bwBuild/getSG/{NO}/{cldh}/{YSSM}")
    public List<bwBuild2> getSG(@PathVariable String NO, @PathVariable String cldh, @PathVariable String YSSM){
        return bwBuildService.getSG(NO, cldh, YSSM);
    }

    // 標準部位項次號
    @GetMapping("/bwBuild/getVersion/{NO}")
    public String getVersion(@PathVariable String NO) {
        return bwBuildService.getVersion(NO+"*%");

    }

    // Insert XXZLS1_B
    @PostMapping("/bwBuild/insertbwBuild/")
    public String insertbwBuild(@RequestBody List<bwBuild> insertBwBuild) {
        bwBuildService.insertXXZLS1_B(insertBwBuild);

        return "success";
    }

    // Update XXZLS1_B
    @PutMapping("/bwBuild/updatebwBuild/")
    public String updatebwBuild(@RequestBody List<bwBuild> updateBwBuild) {
        bwBuildService.updateXXZLS1_B(updateBwBuild);
        return "success";
    }

    // Delete XXZLS1_B
    @DeleteMapping("/bwBuild/deleteXXZLS1_B/{delNO}")
    public String deleteXXZLS1_BB(@PathVariable String delNO) {
        bwBuildService.deleteXXZLS1_B(delNO);
        return "success";
    }

    // Insert XXZLS1_BB
    @PostMapping("/bwBuild/insertXXZLS1_BB/")
    public String insertXXZLS1_BB(@RequestBody List<bwBuild2> insertXXZLS1_BB)  {
        bwBuildService.insertXXZLS1_BB(insertXXZLS1_BB);

        return "success";
    }

    // Delete XXZLS1_BB
    @DeleteMapping("/bwBuild/deleteXXZLS1_BB/")
    public String deleteXXZLS1_BB(@RequestBody List<bwBuild2> deleteXXZLS1_BB) {
        bwBuildService.deleteXXZLS1_BB(deleteXXZLS1_BB);
        return "success";
    }
}
