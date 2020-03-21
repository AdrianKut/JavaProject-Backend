package com.DTeam.eshop.controllers;

import java.util.List;

import java.io.ByteArrayInputStream;

import com.DTeam.eshop.entities.Employee;
import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Payment;
import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.EmployeeService;
import com.DTeam.eshop.services.OrderService;
import com.DTeam.eshop.services.PaymentService;
import com.DTeam.eshop.services.ProductService;
import com.DTeam.eshop.utilities.pdfgenerator.CustomerOrderReportPDF;
import com.DTeam.eshop.utilities.pdfgenerator.DemandReportPDF;
import com.DTeam.eshop.utilities.pdfgenerator.EaringsReportPDF;
import com.DTeam.eshop.utilities.pdfgenerator.EmployeeReportPDF;
import com.DTeam.eshop.utilities.pdfgenerator.OrderReportPDF;
import com.DTeam.eshop.utilities.pdfgenerator.ProductInventoryReportPDF;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.MediaType;

@Controller
public class ReportController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/report/last-month")
    public ResponseEntity<InputStreamResource> earningsReport() throws IOException {
        List<Payment> paymentList = paymentService.getLastMonth();
        ByteArrayInputStream bis = EaringsReportPDF.earningsReport(paymentList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=raportZyskow.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bis));
    }

    @GetMapping("/employee/report/demand-report")
    public ResponseEntity<InputStreamResource> productsEnding() throws IOException {
        List<Product> productList = productService.getProductsEnding();
        ByteArrayInputStream bis = DemandReportPDF.demandReport(productList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=raportZapotrzebowania.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bis));
    }

    @GetMapping("/employee/report/product-inventory")
    public ResponseEntity<InputStreamResource> productsInventory() throws IOException {
        List<Product> productList = productService.listAll();
        ByteArrayInputStream bis = ProductInventoryReportPDF.productInventory(productList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=stanMagazynowy.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bis));
    }

    @GetMapping("/employee/report/order")
    public ResponseEntity<InputStreamResource> orders() throws IOException {
        List<Order> orderList = orderService.getOrderByStatus();
        ByteArrayInputStream bis = OrderReportPDF.order(orderList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=zamowienia.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bis));
    }

    @GetMapping("/employee/report/employee")
    public ResponseEntity<InputStreamResource> employees() throws IOException {
        List<Employee> employeeList = employeeService.listAll();
        ByteArrayInputStream bis = EmployeeReportPDF.employee(employeeList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=pracownicy.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bis));
    }

    @GetMapping("/customer/report/{id}")
    public ResponseEntity<InputStreamResource> customerOrder(@PathVariable("id")Long id) throws IOException {
        Order order = orderService.get(id);
        ByteArrayInputStream bis = CustomerOrderReportPDF.order(order);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=potwierdzenieZakupu.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
        .body(new InputStreamResource(bis));
    }
}