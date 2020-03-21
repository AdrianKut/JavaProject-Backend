package com.DTeam.eshop.controllers;

import java.util.List;

import java.io.ByteArrayInputStream;
import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.services.PaymentService;
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

    @GetMapping("employee/report/lastMonth")
    public ResponseEntity<InputStreamResource> earningsReport() throws IOException {
        List<Payment> paymentList = paymentService.getLastMonth();
        ByteArrayInputStream bis = EaringsReportPDF.earningsReport(paymentList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=raportZysków.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bis));
    }
}