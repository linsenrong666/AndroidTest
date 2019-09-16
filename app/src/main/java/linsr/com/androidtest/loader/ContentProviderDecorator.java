package linsr.com.androidtest.loader;

import android.net.Uri;

/**
 * Created by Linsr
 */
public interface ContentProviderDecorator {

    void startBatchOperation(Uri... uris);

    void endBatchOperation(Uri... uris);

    boolean clearContents();

    void endBatchOperationWithoutNotification(Uri... uris);
}
