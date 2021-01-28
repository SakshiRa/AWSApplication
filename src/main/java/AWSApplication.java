import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AWSApplication {
    private static final AWSCredentials credentials;

    static {
        //put your accesskey and secretkey here
        credentials = new BasicAWSCredentials(
                "<>",
                "<>"
        );
    }

    public static void main(String[] args) {
        //set-up the client
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2)
                .build();

        AWSS3Service awsService = new AWSS3Service(s3client);

        //creating a bucket
        /*if(s3client.doesBucketExist(bucketName)) {
            System.out.println("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            return;
        }
        s3client.createBucket(bucketName); */

        //list all the buckets
        for(Bucket s : awsService.listBuckets() ) {
            System.out.println(s.getName());
        }

        // listing objects
        ObjectListing objectListing = awsService.listObjects("thecodingworld.com");
        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
            System.out.println(os.getKey());
        }

        //downloading an object
        S3Object s3object = awsService.getObject("thecodingworld.com", "error.html");
        System.out.println("started ");
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        System.out.println("this will print ---");

        //uploading object
        awsService.putObject("thecodingworld.com","hello.txt",
                new File("/Users/sakshirathi/Documents/hello.txt"));
    }
}