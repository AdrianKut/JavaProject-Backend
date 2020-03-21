package com.DTeam.eshop.controllers;

import java.util.List;

import java.io.ByteArrayInputStream;
import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.PaymentService;
import com.DTeam.eshop.services.ProductService;
import com.DTeam.eshop.utilities.pdfgenerator.DemandReportPDF;
import com.DTeam.eshop.utilities.pdfgenerator.EaringsReportPDF;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.MediaType;

@Controller
public class ReportController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProductService productService;

    @GetMapping("/employee/report/last-month")
    public ResponseEntity<InputStreamResource> earningsReport() throws IOException {
        List<Payment> paymentList = paymentService.getLastMonth();
        ByteArrayInputStream bis = EaringsReportPDF.earningsReport(paymentList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=raportZysków.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bis));
    }

    @GetMapping("/employee/report/demand-report")
    public ResponseEntity<InputStreamResource> productsEnding() throws IOException {
        List<Product> productList = productService.getProductsEnding();
        ByteArrayInputStream bis = DemandReportPDF.demandReport(productList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=raportZapotrzeboawania.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bis));
    }
}