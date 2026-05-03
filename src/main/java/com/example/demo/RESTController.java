//package com.example.demo;
//
//import com.example.demo.entity.ApplicationRequest;
//import com.example.demo.entity.Courses;
//import com.example.demo.entity.Operators;
//import com.example.demo.repository.CoursesRepository;
//import com.example.demo.repository.OperatorsRepository;
//import com.example.demo.repository.RequestRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@AllArgsConstructor
//@RestController
//@RequestMapping("/api/requests")
//public class RESTController {
//
//    private RequestRepository requestRepository;
//    private CoursesRepository coursesRepository;
//    private OperatorsRepository operatorsRepository;
//
//    @GetMapping
//    public ResponseEntity<List<ApplicationRequest>> getAllRequests() {
//        List<ApplicationRequest> requests = requestRepository.findAll();
//
//        if (requests.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.ok(requests);
//        }
//    }
//
//    @GetMapping(value ="/{id}")
//    public ResponseEntity<ApplicationRequest> getRequestById(@PathVariable Long id) {
//        ApplicationRequest request = requestRepository.findById(id).orElse(null);
//
//        if (request == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(request);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<ApplicationRequest> addRequest(@RequestBody ApplicationRequest request) {
//        if (request.getCourse() == null || request.getCourse().getId() == null) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        Optional<Courses> courseOpt = coursesRepository.findById(request.getCourse().getId());
//        if (courseOpt.isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        Courses course = courseOpt.get();
//
//        request.setCourse(course);
//        request.setHandled(false);
//        requestRepository.save(request);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(request);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
//        ApplicationRequest request = requestRepository.findById(id).orElse(null);
//        if (request == null) {
//            return ResponseEntity.notFound().build();
//        }
//        requestRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//
//    }
//
//    @PutMapping(value = "/{id}")
//    public ResponseEntity<ApplicationRequest> updateRequest(@PathVariable Long id,
//                                                            @RequestBody ApplicationRequest updatedRequest) {
//        Optional <ApplicationRequest> request = requestRepository.findById(id);
//        if (request.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        ApplicationRequest exRequest = request.get();
//
//        if (updatedRequest.getCourse() == null || updatedRequest.getCourse().getId() == null) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        Courses course = coursesRepository.findById(updatedRequest.getCourse().getId()).orElse(null);
//
//        exRequest.setUserName(updatedRequest.getUserName());
//        exRequest.setCourse(course);
//        exRequest.setPhone(updatedRequest.getPhone());
//        requestRepository.save(exRequest);
//
//        return ResponseEntity.ok(exRequest);
//    }
//
//    @PatchMapping(value = "/{id}/handled")
//    public ResponseEntity<ApplicationRequest> changeHandled(@PathVariable Long id) {
//        Optional<ApplicationRequest> request = requestRepository.findById(id);
//        if (request.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        ApplicationRequest requestUpdate = request.get();
//        requestUpdate.setHandled(!requestUpdate.isHandled());
//        requestRepository.save(requestUpdate);
//        return ResponseEntity.ok(requestUpdate);
//    }
//
//    @PostMapping(value = "{reqId}/operators")
//    public ResponseEntity<Operators> addOperators(@PathVariable Long reqId,
//                                                  @RequestBody List<Long> operIds) {
//        Optional<ApplicationRequest> request = requestRepository.findById(reqId);
//        if (request.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        ApplicationRequest requestUpdate = request.get();
//        List<Operators> operators = operatorsRepository.findAllById(operIds);
//        requestUpdate.setOperators(operators);
//        requestRepository.save(requestUpdate);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @DeleteMapping(value = "/{reqId}/operator/{operId}")
//    public ResponseEntity<Operators> deleteOperator(@PathVariable Long reqId, @PathVariable Long operId) {
//        Optional<ApplicationRequest> request = requestRepository.findById(reqId);
//        if (request.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        if (operatorsRepository.findById(operId).isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        ApplicationRequest requestUpdate = request.get();
//        boolean delete = requestUpdate.getOperators().removeIf(operator -> operator.getId().equals(operId));
//        if (!delete) {
//            return ResponseEntity.notFound().build();
//        }
//        requestRepository.save(requestUpdate);
//        return ResponseEntity.noContent().build();
//    }
//
//    // Example of JSON requests:
//
//    // 1) GET http://localhost:8080/api/requests
//    // 2) POST http://localhost:8080/api/requests
//    // json:
//    //    "userName": "Aisha",
//    //    "phone": "+7771387328",
//    //    "course": {"id": 1}
//    // 3) DELETE http://localhost:8080/api/requests/10
//    // 4) PUT http://localhost:8080/api/requests/8
//    // json:
//    // "userName": "Dias",
//    //    "phone": "+7771384343",
//    //    "course": {
//    //        "id": 5
//    //         }
//    // 5) PATCH http://localhost:8080/api/requests/2/handled
//    // 6) POST http://localhost:8080/api/requests/9/operators
//    // json: [1, 2, 3]
//    // 7) DELETE http://localhost:8080/api/requests/9/operator/2
//
//}
