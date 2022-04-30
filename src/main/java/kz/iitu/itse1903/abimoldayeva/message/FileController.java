//package kz.iitu.itse1903.abimoldayeva.message;
//
//import kz.iitu.itse1903.abimoldayeva.database.FileDB;
//import kz.iitu.itse1903.abimoldayeva.message.ResponseFile;
//import kz.iitu.itse1903.abimoldayeva.message.ResponseMessage;
//import kz.iitu.itse1903.abimoldayeva.message.FileStorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping(value = "/files")
//public class FileController {
//    private final FileStorageService fileStorageService;
//
//    @Autowired
//    public FileController(FileStorageService fileStorageService) {
//        this.fileStorageService = fileStorageService;
//    }
//
//    @PostMapping("/upload")
//    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file){
//        String message = "";
//        try{
//            fileStorageService.store(file);
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
//        } catch (IOException e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
//        }
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<List<ResponseFile>> getListFiles(){
//        List<ResponseFile> files = fileStorageService.getAllFiles().map(dbFile -> {
//           String fileDownloadUri = ServletUriComponentsBuilder
//                   .fromCurrentContextPath()
//                   .path("/files/")
//                   .path(dbFile.getId())
//                   .toUriString();
//           return new ResponseFile(dbFile.getName(),
//                        fileDownloadUri,
//                        dbFile.getType(),
//                        dbFile.getData().length);
//        }).collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.OK).body(files);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable String id){
//        FileDB fileDB = fileStorageService.getFile(id);
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//                .body(fileDB.getData());
//
//    }
//
//}
