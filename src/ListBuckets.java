import com.obs.services.ObsClient;
import com.obs.services.model.ListBucketsRequest;
import com.obs.services.model.ObsBucket;
import java.util.List;
class OBSListBucket {
    public static String endPoint = "https://obs.cn-southwest-2.myhuaweicloud.com";
    public static String ak = "KKIM9TEDSNDTINDVDCHW";
    public static String sk ="o6H6ZKOh88AVIHNN3Mci5vCo3hEtKBE1PtzYGKnI";

    public static void listBucket() {
        ObsClient obsClient = new ObsClient(ak, sk, endPoint);
        ListBucketsRequest request = new ListBucketsRequest();
        request.setQueryLocation(true);
        List<ObsBucket> buckets = obsClient.listBuckets(request);
        for (ObsBucket bucket : buckets) {
            System.out.println("BucketName:" + bucket.getBucketName());
            System.out.println("CreationDate:" + bucket.getCreationDate());
            System.out.println("Location:" + bucket.getLocation());
        }
    }

    public static void main(String[] args) {
        listBucket();
    }
}
