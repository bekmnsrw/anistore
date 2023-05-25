package com.bekmnsrw.anistore.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/add-product")
    public String getAddProductPage() { return "admin/add_product"; }

    @GetMapping("/update-product")
    public String getUpdateProductPage() { return "admin/update_product"; }

    @GetMapping("/order-status")
    public String getOrderStatusPage() { return "admin/order_status"; }
}
