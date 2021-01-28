import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;
import java.util.List;

public class AWSS3Service {

    private final AmazonS3 s3client;

    public AWSS3Service() {
        this(new AmazonS3Client() {
        });
    }

    public AWSS3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    //list all buckets
    public List<Bucket> listBuckets() {
        return s3client.listBuckets();
    }

    //uploading object
    public PutObjectResult putObject(String bucketName, String key, File file) {
        return s3client.putObject(bucketName, key, file);
    }

    //listing objects
    public ObjectListing listObjects(String bucketName) {
        return s3client.listObjects(bucketName);
    }

    //get an object
    public S3Object getObject(String bucketName, String objectKey) {
        return s3client.getObject(bucketName, objectKey);
    }
}