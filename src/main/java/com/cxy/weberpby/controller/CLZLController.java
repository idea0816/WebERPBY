package com.cxy.weberpby.controller;

import com.cxy.weberpby.dto.CLZLQueryParams;
import com.cxy.weberpby.model.CLZL;
import com.cxy.weberpby.model.clzlsl;
import com.cxy.weberpby.service.CLZLService;
import com.cxy.weberpby.service.clzlslService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/4/21
 * @Description 材料資料 - 含材料、配方、配方组成WXYZ、物性、配方单价(頁面)
 * <p>
 *
 * public List<clzlsl> clzlszList() {   // 基礎配方頁面-取得配方List(含W.Y.Y.Z)
 * public String clzlsl(Model model)    // 配方页面 // Get 配方主资料    // 取得所有原物料 AW
 * public List<clzlsl> clzlslList(@PathVariable String cldh)   // 配方页面-取得配方List(含W.Y.Y.Z)
 */

@Controller
public class CLZLController {

    @Autowired
    private CLZLService clzlService;

    @Autowired
    private clzlslService clzlslService;

    // 配方類查詢條件設定
    CLZLQueryParams clzlQueryParams;


    // 基礎配方頁面-取得配方List(含W.Y.Y.Z)
//    @GetMapping("/clzlszList/{cldh}")
//    @ResponseBody
//    public List<clzlsl> clzlszList(@PathVariable String cldh) {
//        return clzlslService.getVofclzlsz(cldh);
//
//    }

    // 配方页面
    @GetMapping("/clzlsl")
    public String clzlsl(Model model) {
        clzlQueryParams = new CLZLQueryParams();
        // Get 配方主资料
        clzlQueryParams.setLb("V");
        model.addAttribute("getAllVRList", clzlService.getCLZL(clzlQueryParams));
        // 取得所有原物料 AW
        clzlQueryParams.setLb("A");
        model.addAttribute("getAllAList", clzlService.getCLZL(clzlQueryParams));
        return "clzlsl";
    }

    // 配方页面-取得配方List(含W.Y.Y.Z)
    @GetMapping("/clzlslList/{cldh}")
    @ResponseBody
    public List<clzlsl> clzlslList(@PathVariable String cldh) {
        return clzlslService.getclzlsl(cldh);
    }

    // Update配方資料List(含W.Y.Y.Z)
    @PutMapping("CLZLs/{cldh}")
    @ResponseBody
    public List<CLZL> updateclzlsl(@PathVariable String cldh) {
        // Check cldh
        // List<CLZL> clzl = clzlService.getCLZL(cldh, null);
//        if( clzl.size() == 0){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }


//        return ResponseEntity.status(HttpStatus.OK).body(CLZL);

        return null;
    }

}
