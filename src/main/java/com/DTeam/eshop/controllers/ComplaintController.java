package com.DTeam.eshop.controllers;

import java.util.List;

import com.DTeam.eshop.entities.Complaint;
import com.DTeam.eshop.entities.Order;
import com.DTeam.eshop.entities.Product;
import com.DTeam.eshop.services.ComplaintService;
import com.DTeam.eshop.services.OrderService;
import com.DTeam.eshop.services.ProductService;
import com.DTeam.eshop.utilities.CustomErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class ComplaintController {

    @Autowired private ComplaintService complaintService;

    @Autowired private OrderService orderService;

    @Autowired private ProductService productService;

    //Retrieve all complaints
    @GetMapping("/complaints")
    public ResponseEntity<List<Complaint>> getComplaints() {
        List<Complaint> complaints = complaintService.listAll();
        if(complaints.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Complaint>>(complaints, HttpStatus.OK);
    }

    //Retrieve single complaint
    @GetMapping("/complaints/{id}")
    public ResponseEntity<?> getComplaint(@PathVariable("id")Long complaintId){
        if(complaintService.isComplaintExist(complaintId)){
            Complaint complaint = complaintService.get(complaintId);
            return new ResponseEntity<Complaint>(complaint, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorType("Complaint with id " + complaintId + " not found."), HttpStatus.NOT_FOUND);
    }

    //Create a complaint
    @PostMapping("/complaints")
    public ResponseEntity<?> createComplaint(@RequestBody Complaint complaint, UriComponentsBuilder ucBuilder) {
        Long id = complaint.getComplaintId();
        if(id != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A complaint with id " + id + 
            " already exist."), HttpStatus.CONFLICT);
        }
        complaintService.save(complaint);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/complaints/{id}").buildAndExpand(complaint.getComplaintId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

       //Update a complaint
       @PutMapping("/complaints/{id}")
       public ResponseEntity<?> updateComplaint(@PathVariable("id")Long complaintId, @RequestBody Complaint complaint){
           if(complaintService.isComplaintExist(complaintId)){
            Complaint currentComplaint = complaintService.get(complaintId);
            currentComplaint.setNotificationDate(complaint.getNotificationDate());
            currentComplaint.setDescription(complaint.getDescription());
            currentComplaint.setComplaintStatus(complaint.getComplaintStatus());
            complaintService.save(currentComplaint);
   
               return new ResponseEntity<Complaint>(currentComplaint, HttpStatus.OK);
           }
   
           return new ResponseEntity<>(new CustomErrorType("Unable to update. Complaint with id " + complaintId + " not found."),
           HttpStatus.NOT_FOUND);
       }

       //Delete a complaint
    @DeleteMapping("/complaints/{id}")
    public ResponseEntity<?> deleteComplaint(@PathVariable("id")Long complaintId){
        if(complaintService.isComplaintExist(complaintId)){
            complaintService.delete(complaintId);
            return new ResponseEntity<Complaint>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new CustomErrorType("Unable to delete. Complaint with id " + complaintId + " not found."),
        HttpStatus.NOT_FOUND);
    }

    //Retrieve a orders
    @GetMapping("/complaints/{id}/orders")
    public ResponseEntity<?> getOrder(@PathVariable("id")Long complaintId){
        if(!complaintService.isComplaintExist(complaintId)){
            return new ResponseEntity<>(new CustomErrorType("Complaint with id " + complaintId + " not found."), HttpStatus.NOT_FOUND);
        }
        Complaint complaint = complaintService.get(complaintId);
        if(complaint.getOrder() == null){
            return new ResponseEntity<>(new CustomErrorType("Complaint with id " + complaintId + " has no order assigned yet."), HttpStatus.NOT_FOUND); 
        }
        Order order = complaint.getOrder();
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    //Create a orders
    @PostMapping("/complaints/{id}/orders")
    public ResponseEntity<?> createOrder(@PathVariable("id")Long complaintId, @RequestBody Order order, UriComponentsBuilder ucBuilder){
        if(!complaintService.isComplaintExist(complaintId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Complaint with id " + complaintId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        Complaint complaint = complaintService.get(complaintId);
        if(complaint.getOrder() != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Complaint with id " + complaintId + " has already order."),
            HttpStatus.CONFLICT);
        }
        orderService.save(order);
        complaint.setOrder(order);
        complaintService.save(complaint);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/orders/{id}").buildAndExpand(complaint.getComplaintId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //Update a orders
    @PutMapping("/complaints/{complaintid}/orders/{orderid}")
    public ResponseEntity<?> updateOrders(@PathVariable("complaintid")Long complaintId, 
    @PathVariable("orderid")Long orderId, @RequestBody Order order){
        if(!complaintService.isComplaintExist(complaintId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Complaint with id " + complaintId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        if(!orderService.isOrderExist(orderId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Order with id " + orderId + " not found."),
            HttpStatus.NOT_FOUND); 
        }
        Order currentOrder = orderService.get(orderId);
        currentOrder.setPurchaseDate(order.getPurchaseDate());
        currentOrder.setShipmentDate(order.getShipmentDate());
        currentOrder.setOrderStatus(order.getOrderStatus());
        orderService.save(currentOrder);
        return new ResponseEntity<Order>(currentOrder, HttpStatus.OK);
    }

     //Create the association
     @PostMapping("/complaints/{complaintid}/orders/{orderid}")
     public ResponseEntity<?> associateOrder(@PathVariable("complaintid")Long complaintId, 
     @PathVariable("orderid")Long orderId){
         if(!complaintService.isComplaintExist(complaintId)){
             return new ResponseEntity<>(new CustomErrorType("Unable to associate. Complaint with id " + complaintId + " not found."),
             HttpStatus.NOT_FOUND);
         }
         if(!orderService.isOrderExist(orderId)){
             return new ResponseEntity<>(new CustomErrorType("Unable to associate. Order with id " + orderId + " not found."),
             HttpStatus.NOT_FOUND); 
         }
         Complaint complaint = complaintService.get(complaintId);
         if(complaint.getOrder() != null){
             return new ResponseEntity<>(new CustomErrorType("Unable to associate. Complaint with id " + complaintId + " has already order."),
             HttpStatus.CONFLICT);
         }
         complaint.setOrder(orderService.get(orderId));
         complaintService.save(complaint);
         return new ResponseEntity<Complaint>(complaint, HttpStatus.CREATED);
     }

     //Retrieve a products
    @GetMapping("/complaints/{id}/products")
    public ResponseEntity<?> getProduct(@PathVariable("id")Long complaintId){
        if(!complaintService.isComplaintExist(complaintId)){
            return new ResponseEntity<>(new CustomErrorType("Complaint with id " + complaintId + " not found."), HttpStatus.NOT_FOUND);
        }
        Complaint complaint = complaintService.get(complaintId);
        if(complaint.getProduct() == null){
            return new ResponseEntity<>(new CustomErrorType("Complaint with id " + complaintId + " has no product assigned yet."), HttpStatus.NOT_FOUND); 
        }
        Product product = complaint.getProduct();
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    //Create a product
    @PostMapping("/complaints/{id}/products")
    public ResponseEntity<?> createAdress(@PathVariable("id")Long complaintId, @RequestBody Product product, UriComponentsBuilder ucBuilder){
        if(!complaintService.isComplaintExist(complaintId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Complaint with id " + complaintId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        Complaint complaint = complaintService.get(complaintId);
        if(complaint.getProduct() != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Complaint with id " + complaintId + " has already product."),
            HttpStatus.CONFLICT);
        }
        productService.save(product);
        complaint.setProduct(product);
        complaintService.save(complaint);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/products/{id}").buildAndExpand(complaint.getComplaintId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    //Update a product
    @PutMapping("/complaints/{complaintid}/products/{productid}")
    public ResponseEntity<?> updateProduct(@PathVariable("complaintid")Long complaintId, 
    @PathVariable("productid")Long productId, @RequestBody Product product){
        if(!complaintService.isComplaintExist(complaintId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Complaint with id " + complaintId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        if(!productService.isProductExist(productId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Product with id " + productId + " not found."),
            HttpStatus.NOT_FOUND); 
        }
        Product currentProduct = productService.get(productId);
        currentProduct.setName(product.getName());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setQuantity(product.getQuantity());
        productService.save(currentProduct);
        return new ResponseEntity<Product>(currentProduct, HttpStatus.OK);
    }

    //Create the association
    @PostMapping("/complaints/{complaintid}/products/{productid}")
    public ResponseEntity<?> associateProduct(@PathVariable("complaintid")Long complaintId, 
    @PathVariable("productid")Long productId){
        if(!complaintService.isComplaintExist(complaintId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. Complaint with id " + complaintId + " not found."),
            HttpStatus.NOT_FOUND);
        }
        if(!productService.isProductExist(productId)){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. Product with id " + productId + " not found."),
            HttpStatus.NOT_FOUND); 
        }
        Complaint complaint = complaintService.get(complaintId);
        if(complaint.getProduct() != null){
            return new ResponseEntity<>(new CustomErrorType("Unable to associate. Complaint with id " + complaintId + " has already product."),
            HttpStatus.CONFLICT);
        }
        complaint.setProduct(productService.get(productId));
        complaintService.save(complaint);
        return new ResponseEntity<Complaint>(complaint, HttpStatus.CREATED);
    }

}