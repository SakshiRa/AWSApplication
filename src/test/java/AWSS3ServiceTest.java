import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AWSS3ServiceTest {

    private static final String BUCKET_NAME = "bucket_name";
    private static final String KEY_NAME = "key_name";
    private static final String BUCKET_NAME2 = "bucket_name2";
    private static final String KEY_NAME2 = "key_name2";

    private AmazonS3 s3;
    private AWSS3Service service;

    @BeforeEach
    void setUp() {
        s3 = mock(AmazonS3.class);
        service = new AWSS3Service(s3);
    }

    @Test
    public void whenInitializingAWSS3Service_thenNotNull(){
        assertThat(new AWSS3Service()).isNotNull();
    }

    @Test
    public void whenVerifyingListBuckets_thenCorrect() {
        service.listBuckets();
        verify(s3).listBuckets();
    }

    @Test
    public void whenVerifyingPutObject_thenCorrect() {
        File file = mock(File.class);
        PutObjectResult result = mock(PutObjectResult.class);
        when(s3.putObject(anyString(), anyString(), (File) any())).thenReturn(result);

        assertThat(service.putObject(BUCKET_NAME, KEY_NAME, file)).isEqualTo(result);
        verify(s3).putObject(BUCKET_NAME, KEY_NAME, file);
    }

    @Test
    public void whenVerifyingListObjects_thenCorrect() {
        service.listObjects(BUCKET_NAME);
        verify(s3).listObjects(BUCKET_NAME);
    }

    @Test
    public void whenVerifyingGetObject_thenCorrect() {
        service.getObject(BUCKET_NAME, KEY_NAME);
        verify(s3).getObject(BUCKET_NAME, KEY_NAME);
    }
}
