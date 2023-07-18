package tn.esprit.healthcloud.services;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;


@Service
public class FirebaseImageService implements IImageService {

    @Autowired
    Properties properties;

    @EventListener
    public void init(ApplicationReadyEvent event) {

        // initialize Firebase

        try {

            ClassPathResource serviceAccount = new ClassPathResource("healthcloudproject-70ea7-firebase-adminsdk-6xj2h-92f9e0e28d.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                    .setStorageBucket(properties.getBucketName())
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

    @Override
    public String getImageUrl(String name) {
        return String.format(properties.imageUrl, name);
    }

    @Override
    public String save(MultipartFile file) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket();

        String name = file.getOriginalFilename();

        bucket.create(name, file.getBytes(), file.getContentType());

        return name;
    }

  

    @Override
    public void delete(String name) throws IOException {

        Bucket bucket = StorageClient.getInstance().bucket();

        if (StringUtils.isEmpty(name)) {
            throw new IOException("invalid file name");
        }

        Blob blob = bucket.get(name);

        if (blob == null) {
            throw new IOException("file not found");
        }

        blob.delete();
    }

    @Data
    @Configuration
    @ConfigurationProperties(prefix = "firebase")
    public class Properties {

        private String bucketName;

        private String imageUrl;
    }
    
    
    
    // Méthode pour télécharger une image à partir de Firebase Storage
    public String download(String fileName) throws IOException {
        String destFileName = fileName;     // to set random strinh for destination file name
        String destFilePath = "C:/" + destFileName;                                    // to set destination file path
        
        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("C:/Users/HP/Desktop/healthCloud/healthCloud/src/main/resources/healthcloudproject-70ea7-firebase-adminsdk-6xj2h-92f9e0e28d.json"));
        com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("healthcloudproject-70ea7.appspot.com", fileName));
        blob.downloadTo(Paths.get(destFilePath));
        return "ok";
    }
    
    
    
    
    
    
    
    

}